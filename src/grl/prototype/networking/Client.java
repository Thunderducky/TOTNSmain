package grl.prototype.networking;

import grl.prototype.messaging.Message;
import grl.prototype.networking.client.ChatConsole;
import grl.prototype.networking.client.ClientMessageProcessor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class Client{
	private static final int VERSION = 1;
	private String username,password,address;
	private int port;
	private NetworkClient client;
	public Client(String address, int port, String username, String password){
		this.address = address;
		this.port = port;
		this.username = username;
		this.password = password;
		client = new NetworkClient();
		client.start();
	}
	public synchronized void stop(){
		client.disconnect();
	}
	public synchronized void sendMessage(Message message){
		client.sendMessage(message);
	}
	public String getUsername(){
		return username;
	}
	public static void main(String[] args){
		String ip = ChatConsole.getInput("Server IP:");
		String username = ChatConsole.getInput("Username:");
		String password = ChatConsole.getInput("Password:");
		
		Client client = new Client(ip,8888,username,password);
		ChatConsole reader = new ChatConsole(client);
		reader.start();
	}
	
	class NetworkClient extends Thread{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Queue<Message> messageQueue = new LinkedList<Message>();
		boolean stop = false;
		ClientMessageProcessor processor = new ClientMessageProcessor();
		NetworkClient(){
			super();
		}
		public synchronized void sendMessage(Message message){
			messageQueue.add(message);
		}
		public synchronized void disconnect(){
			stop = true;
		}
		@Override
		public void run() {
			try {
				Socket socket = new Socket(Client.this.address,Client.this.port);
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				Message initial = new Message("Client.Connect");
				initial.setArgument("username", Client.this.username);
				initial.setArgument("password", Client.this.password);
				initial.setArgument("version", Client.VERSION);
				oos.writeObject(new Packet(initial));
				oos.flush();
				Packet respose = (Packet)ois.readObject();
				
				while(!stop){
					Packet sendPacket;
					if(!messageQueue.isEmpty()){
						sendPacket = new Packet(messageQueue.poll());
					}
					else{ //PING
						sendPacket = new Packet();
					}
					oos.writeObject(sendPacket);
					oos.flush();
					respose = (Packet)ois.readObject();
					for(Message message : respose.getMessages()){
						processor.processMessage(message);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Message closing = new Message("Client.Disconnect");
				initial.setArgument("username", "george");
				oos.writeObject(new Packet(closing));
				respose = (Packet)ois.readObject();
				oos.close();
				ois.close();
				socket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


