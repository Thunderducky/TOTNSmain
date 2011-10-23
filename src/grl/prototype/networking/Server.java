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
				new Thread(conn).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	private synchronized void registerClient(String username, Connection conn){
		if(clientConnections.containsKey(username)){
			disconnectClient(username);
			clientConnections.put(username,conn);
		}
	}
	private synchronized void disconnectClient(String username){
		if(clientConnections.containsKey(username)){
			Connection conn = clientConnections.get(username);
		}
	}
	public static void main(String[] args){
		new Thread(new Server()).start();
	}

	class Connection implements Runnable{
		Socket clientSocket;
		ObjectInputStream inputStream = null;
		ObjectOutputStream outputStream = null;
		private boolean isActive  = false;
		Connection(Socket clientSocket){
			this.clientSocket = clientSocket;
			try {
				inputStream = new ObjectInputStream(clientSocket.getInputStream());
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
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
						String clientUsername = initial.getArgumentString("username");
						String clientPassword = initial.getArgumentString("password");
						String clientVersion = initial.getArgumentString("version");
						System.out.println("Client Connected-username:"+clientUsername+" password:"+clientPassword+" version:"+clientVersion);
					}
				}
				outputStream.writeObject(new Packet());

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
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
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


