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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.linearoja.Assets;
import com.linearoja.cm.Script;

public class Server extends Thread{
	/* STATIC DATA AND METHODS*/
	static int currentId;
	static Server instance;
	public static Server getInstance(){
		if(instance==null)
			instance = new Server();
		return instance;
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

	public Server(){
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMessageTo(Message message, String to){
		if(clientConnections.containsKey(to)){
			clientConnections.get(to).sendMessage(message);
		}
	}
	public void sendMessageAll(Message message){
		for(ClientConnection conn:clientConnections.values()){
			conn.sendMessage(message);
		}
	}
	public Collection<String> getConnectedUsers(){
		return clientConnections.keySet();
	}
	public void setClientConnectionListener(ClientConnectionListener listener){
		this.connectionListener = listener;
	}
	public void setRootMessageProcessor(RootMessageProcessor messageProcessor){
		this.messageProcessor = messageProcessor;
	}
	public void run(){
		startTime = System.currentTimeMillis();
		while(true){
			try {
				Socket client = serverSocket.accept();
				ClientConnection conn = new ClientConnection(client);
				conn.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	private synchronized void registerClient(String username, ClientConnection conn){
		if(clientConnections.containsKey(username)){
			disconnectClient(username);
		}
		clientConnections.put(username,conn);
		conn.id = getConnectionId();
	}
	public synchronized boolean clientConnected(String username){
		if(clientConnections.containsKey(username)){
			ClientConnection conn = clientConnections.get(username);
			return conn.isActive();
		}
		else{
			return false;
		}
	}
	public synchronized void disconnectClient(String username){
		if(clientConnections.containsKey(username)){
			ClientConnection conn = clientConnections.get(username);
			System.out.println("Disconnecting client:"+conn.getConnectionId()+"@"+username);
			conn.disconnectClient();
		}
	}
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
	public long getUptime(){
		return System.currentTimeMillis() - startTime;
	}
	private static int getConnectionId(){
		return currentId ++;
	}

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
		public int getConnectionId(){
			return id;
		}
		public synchronized boolean isActive(){
			return isActive  ;
		}
		public synchronized void disconnectClient(){
			isActive = false;
		}
		public void sendMessage(Message... messages){
			outMessages.addAll(Arrays.asList(messages));
		}
		public String toString(){
			return clientUsername+"["+id+"]@"+clientIp;
		}
		public long getPing(){
			return pingTime;
		}
		@Override
		public void run() {
			try {
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

				while(isActive){
					packet = (Packet)inputStream.readObject();
					long currentTime = System.currentTimeMillis();
					pingTime = currentTime-lastPing;
					lastPing = currentTime;
					
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
					if(!packet.hasMessages()){
					}
					for(Message message : packet.getMessages()){
						if(message instanceof DisconnectMessage){
							Server.this.connectionListener.onClientDisconnect(clientUsername);
							isActive = false;
							break;
						}
						else{
							Server.this.messageProcessor.processMessage(message);
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

	}
}


