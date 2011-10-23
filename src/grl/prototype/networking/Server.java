package grl.prototype.networking;

import grl.prototype.messaging.Message;
import grl.prototype.scripting.Console;
import grl.prototype.scripting.InteractiveConsole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.linearoja.Assets;
import com.linearoja.cm.Script;

public class Server extends Thread{
	/* STATIC DATA AND METHODS*/
	private static Server instance;
	static int currentId;

	public static void startServer(){
		instance = new Server();
		instance.start();
	}
	public static Server getInstance(){
		return instance;
	}


	/* INSTANCE DATA AND METHODS */
	private ServerSocket serverSocket;
	private HashMap<String,Connection> clientConnections = new HashMap<String,Connection>();
	private long startTime;

	private Server(){
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
		for(Connection conn:clientConnections.values()){
			conn.sendMessage(message);
		}
	}
	public void run(){
		startTime = System.currentTimeMillis();
		while(true){
			try {
				Socket client = serverSocket.accept();
				Connection conn = new Connection(client);
				conn.sendMessage(new Message("Chat.Broadcast"));
				conn.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	private synchronized void registerClient(String username, Connection conn){
		if(clientConnections.containsKey(username)){
			disconnectClient(username);
		}
		clientConnections.put(username,conn);
		conn.id = getConnectionId();
	}
	public synchronized boolean clientConnected(String username){
		if(clientConnections.containsKey(username)){
			Connection conn = clientConnections.get(username);
			return conn.isActive();
		}
		else{
			return false;
		}
	}
	public synchronized void disconnectClient(String username){
		if(clientConnections.containsKey(username)){
			Connection conn = clientConnections.get(username);
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
		
		
		if(clientConnections.isEmpty()){
			status += "No Connected Clients\n";
		}
		else{
			status += "Connected Clients:\n";
			for(Connection conn:clientConnections.values()){
				if(conn.isActive())
				status += "\t"+conn.toString()+"\n"; 
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
	public static void main(String[] args){
		Script serverScript = Assets.scripts.server;
		serverScript.loadData();
		Console.exec(serverScript);
		Server.startServer();
		new InteractiveConsole().start();
	}

	class Connection extends Thread{
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
		Connection(Socket clientSocket){
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
		@Override
		public void run() {
			try {
				Packet packet;

				packet = (Packet)inputStream.readObject();
				if(packet.hasMessages()){
					Message initial = packet.getMessages().get(0);
					if(initial.getType().equals("Client.Connect")){
						clientUsername = initial.getArgumentString("username");
						clientPassword = initial.getArgumentString("password");
						clientVersion = initial.getArgumentString("version");
						System.out.println("Client Connected-username:"+clientUsername+" password:"+clientPassword+" version:"+clientVersion);
					}
				}
				outputStream.writeObject(new Packet());

				Server.this.registerClient(clientUsername, this);

				isActive = true;

				while(isActive){
					packet = (Packet)inputStream.readObject();
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
						//System.out.println("Client Ping");
					}
					for(Message message : packet.getMessages()){
						if(message.getType().equals("Client.Disconnect")){
							System.out.println("Client Disconnect");
							isActive = false;
							break;
						}
						System.out.println("Received From Client: "+message.getType());
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


