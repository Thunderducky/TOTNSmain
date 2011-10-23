package grl.prototype.networking;

import grl.prototype.messaging.Message;

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
	public static void main(String[] args){
		Client client = new Client("127.0.0.1",8888,"eyce9000","secret");
		client.sendMessage(new Message("Cities.Something"));
		client.sendMessage(new Message("Cities.Something1"));
		client.sendMessage(new Message("Cities.Something2"));
		Client client2 = new Client("127.0.0.1",8888,"scottzillaster","secret2");
		client2.sendMessage(new Message("Other.Message"));
	}
	
	class NetworkClient extends Thread{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Queue<Message> messageQueue = new LinkedList<Message>();
		boolean stop = false;
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
						System.out.println("Received From Server: "+message.getType());
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


