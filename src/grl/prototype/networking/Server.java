package grl.prototype.networking;

import grl.prototype.example.server.ServerConsole;
import grl.prototype.example.server.messages.ServerInMessageProcessor;
import grl.prototype.example.server.messages.chat.ChatInProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.RootMessageProcessor;
import grl.prototype.networking.client.messages.ClientMessage;
import grl.prototype.networking.client.messages.ConnectMessage;
import grl.prototype.networking.client.messages.DisconnectMessage;
import grl.prototype.networking.server.ClientConnectionListener;
import grl.prototype.scripting.Console;
import grl.prototype.scripting.InteractiveConsole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.linearoja.Assets;
import com.linearoja.cm.Script;

/**
 * A TCP Socket server. Only one instance can be active at one time. Many NetworkClients may connect to the server at the same time.
 * @see NetworkClient
 * @author eyce9000
 *
 */
public class Server extends Thread{
	/* STATIC DATA AND METHODS*/
	private static int currentId;
	private static HashMap<Integer,Server> servers = new HashMap<Integer,Server>();
	
	/**
	 * Only one static instance of the server is available. This is the only means of accessing and creating a server.
	 * The server may or may not be running.
	 * @return the current server instance
	 */
	public static Server getInstance(){
		if(!servers.isEmpty()){
			return servers.values().iterator().next();
		}
		return null;
	}
	
	public static Server getInstance(int socket){
		if(servers.containsKey(socket)){
			return servers.get(socket);
		}
		Server server = new Server(socket);
		servers.put(socket, server);
		return server;
	}

	/* INSTANCE DATA AND METHODS */
	private ServerSocket serverSocket;
	private HashMap<String,ClientConnection> clientConnections = new HashMap<String,ClientConnection>();
	private long startTime;
	private RootMessageProcessor messageProcessor ;
	private ClientConnectionListener connectionListener = new ClientConnectionListener(){
		@Override
		public void onClientConnect(String username) {
			System.out.println("User "+username+" connected");
		}
		@Override
		public void onClientDisconnect(String username) {
			System.out.println("User "+username+" disconnected");

		}
	};

	private Server(int socket){
		try {
			serverSocket = new ServerSocket(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the message to the out-queue of messages for the client with the specified username. If no such user is connected, nothing is done. 
	 * <br>Non-blocking.
	 * @param message the message to send
	 * @param to the username of the receiving client
	 */
	public void sendMessageTo(Message message, String to){
		if(clientConnections.containsKey(to)){
			clientConnections.get(to).sendMessage(message);
		}
	}
	
	/**
	 * Adds the message to the out-queue of messages for each client. 
	 * <br>Non-blocking.
	 * @param message the message to broadcast
	 */
	public void sendMessageAll(Message message){
		synchronized(message){
			for(ClientConnection conn:clientConnections.values()){
				conn.sendMessage(message);
			}
		}
	}
	
	
	/**
	 * 
	 * @return a list of the usernames of currently connected clients
	 */
	public List<String> getConnectedUsers(){
		return new ArrayList<String>(clientConnections.keySet());
	}
	
	
	
	/**
	 * The ClientConnectionListener is a listener which is called whenever a client connects or disconnects.
	 * @param listener the ClientConnectionListener for this server
	 */
	public void setClientConnectionListener(ClientConnectionListener listener){
		this.connectionListener = listener;
	}
	
	
	/**
	 * The RootMessageProcessor is passed all incoming messages from all of the clients.
	 * @param messageProcessor the RootMessageProcessor to use for this server
	 */
	public void setRootMessageProcessor(RootMessageProcessor messageProcessor){
		this.messageProcessor = messageProcessor;
	}
	
	
	/**
	 * Determines if a client with the given username is connected
	 * @param username the username of the client
	 * @return True if there is a client connected with that username. False otherwise.
	 */
	public synchronized boolean isClientConnected(String username){
		if(clientConnections.containsKey(username)){
			ClientConnection conn = clientConnections.get(username);
			return conn.isActive();
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * Disconnects the client with the given username. If no such client is connected, nothing is done.
	 * @param username the username of the client to be disconnected.
	 */
	public synchronized void disconnectClient(String username){
		if(clientConnections.containsKey(username)){
			ClientConnection conn = clientConnections.get(username);
			System.out.println("Disconnecting client:"+conn.getConnectionId()+"@"+username);
			conn.disconnectClient();
		}
	}
	
	
	/**
	 * The server status is a user readable description containing uptime and a summary of current clients connected
	 * @return a description of the current server status
	 */
	public String getStatus(){
		String status = "";
		double uptime = (double)getUptime();
		//uptime in minutes
		uptime /= 1000*60;

		if(uptime < 60){
			status += String.format("Uptime: %.2f minutes\n", uptime);
		}
		else{
			status += String.format("Uptime: %.2f hours\n", uptime/60);
		}

		status+= "Host: " + serverSocket.getInetAddress().getHostAddress()+"\n";
		if(clientConnections.isEmpty()){
			status += "No Connected Clients\n";
		}
		else{
			status += "Connected Clients:\n";
			for(ClientConnection conn:clientConnections.values()){
				if(conn.isActive())
					status += "\t"+conn.toString()+"\t"+conn.getPing()+"\n"; 
			}
		}
		return status;
	}
	
	
	/**
	 * Returns the uptime of the server in milliseconds
	 * @return the uptime of the server in milliseconds
	 */
	public long getUptime(){
		return System.currentTimeMillis() - startTime;
	}
	
	
	private synchronized void registerClient(String username, ClientConnection conn){
		if(clientConnections.containsKey(username)){
			disconnectClient(username);
		}
		clientConnections.put(username,conn);
		conn.id = getConnectionId();
	}
	
	
	private static int getConnectionId(){
		return currentId ++;
	}
	
	
	public void run(){
		startTime = System.currentTimeMillis();
		while(true){
			try {
				Socket client = serverSocket.accept();
				ClientConnection conn = new ClientConnection(client);
				conn.start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	

	
	/**
	 * The client connection class is a thread that handles an individual client socket connection. 
	 * The thread stays running as long as the client is connected, only terminating if the client disconnects or if disconnectClient() is called.
	 * @see ClientConnection#disconnectClient()
	 * @author eyce9000
	 *
	 */
	class ClientConnection extends Thread{
		Socket clientSocket;
		ObjectInputStream inputStream = null;
		ObjectOutputStream outputStream = null;
		Queue<Message> outMessages = new LinkedList<Message>();
		private boolean isActive  = false;
		private String clientUsername;
		private String clientPassword;
		private String clientVersion;
		private String clientIp;
		private int id;
		private long lastPing,pingTime;
		ClientConnection(Socket clientSocket){
			this.clientSocket = clientSocket;
			clientIp = clientSocket.getInetAddress().toString();
			try {
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * The connection ID is the unique numeric identifier for the ClientConnection. 
		 * If another client connects with the same username, it will be assigned a different connection ID.
		 * 
		 * @return the unique connection identification number for this client connection
		 */
		public int getConnectionId(){
			return id;
		}
		
		/**
		 * The ClientConnection is active if it is still receiving packets from the client and the client has not been forceably disconnected yet.
		 * @see ClientConnection#disconnectClient()
		 * @return if true, this connection is active
		 */
		public synchronized boolean isActive(){
			return isActive  ;
		}
		
		/**
		 * Forces this client connection to disconnect cleanly. This will terminate the ClientConnection.
		 */
		public synchronized void disconnectClient(){
			isActive = false;
		}
		
		/**
		 * Non-blocking: adds messages to the queue to be sent to the client
		 * @param messages to add to the connection queue
		 */
		public void sendMessage(Message... messages){
			synchronized(messages){
				if(messages.length>0)
					outMessages.addAll(Arrays.asList(messages));
			}
		}
		/**
		 * Returns the packet ping time.
		 * @return the time in milliseconds between recieving packets
		 */
		public long getPing(){
			return pingTime;
		}
		@Override
		public void run() {
			try {
				
				/**
				 * Login packet
				 */
				Packet packet;
				packet = (Packet)inputStream.readObject();
				if(packet.hasMessages()){
					Message initial = packet.getMessages().get(0);
					if(initial instanceof ConnectMessage){
						ConnectMessage connectMessage = (ConnectMessage)initial;
						clientUsername = connectMessage.getUsername();
						clientPassword = connectMessage.getPassword();
						clientVersion = connectMessage.getVersion()+"";
						Server.this.connectionListener.onClientConnect(clientUsername);
					}
				}
				outputStream.writeObject(new Packet());

				Server.this.registerClient(clientUsername, this);

				isActive = true;

				/**
				 * Main Loop of receiving and sending
				 */
				while(isActive){
					/**
					 * Read Packet
					 */
					packet = (Packet)inputStream.readObject();
					
					
					long currentTime = System.currentTimeMillis();
					pingTime = currentTime-lastPing;
					lastPing = currentTime;

					
					/**
					 * Send Messages
					 */
					Packet outPacket;
					if(outMessages.isEmpty()){
						outPacket = new Packet();
					}
					else{
						outPacket = new Packet(outMessages);
						outMessages.clear();
					}
					outputStream.writeObject(outPacket);
					outputStream.flush();
					
					/**
					 * Process messages in packet
					 */
					if(!packet.hasMessages()){
					}
					for(Message message : packet.getMessages()){
						if(message instanceof DisconnectMessage){
							Server.this.connectionListener.onClientDisconnect(clientUsername);
							isActive = false;
							break;
						}
						else{
							Server.this.messageProcessor.addMessage(message);
						}
					}
				}
				inputStream.close();
				outputStream.close();
				clientSocket.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public String toString(){
			return clientUsername+"["+id+"]@"+clientIp;
		}
	}
	
}


