package grl.prototype.networking;

import grl.prototype.messaging.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable{
	ServerSocket serverSocket;
	HashMap<String,Connection> clientConnections = new HashMap<String,Connection>();
	static int currentId;
	public Server(){
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMessageTo(Message message, String to){

	}
	public void sendMessageAll(Message message){

	}
	public void run(){
		while(true){
			try {
				Socket client = serverSocket.accept();
				Connection conn = new Connection(client);
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
	private synchronized void disconnectClient(String username){
		if(clientConnections.containsKey(username)){
			Connection conn = clientConnections.get(username);
			System.out.println("Disconnecting client:"+conn.getConnectionId()+"@"+username);
			conn.disconnectClient();
		}
	}

	public static int getConnectionId(){
		return currentId ++;
	}
	public static void main(String[] args){
		new Thread(new Server()).start();
	}

	class Connection extends Thread{
		Socket clientSocket;
		ObjectInputStream inputStream = null;
		ObjectOutputStream outputStream = null;
		private boolean isActive  = false;
		private String clientUsername;
		private String clientPassword;
		private String clientVersion;
		private int id;
		Connection(Socket clientSocket){
			this.clientSocket = clientSocket;
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
					outputStream.writeObject(new Packet());
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


