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
	private NetworkClient client;
	public Client(){
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
		Client client = new Client();
		client.sendMessage(new Message("Cities.Something"));
		client.sendMessage(new Message("Cities.Something1"));
		client.sendMessage(new Message("Cities.Something2"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.stop();
	}
	
	
}

class NetworkClient extends Thread{
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	Queue<Message> messageQueue = new LinkedList<Message>();
	boolean stop = false;
	NetworkClient(){
		
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
			Socket socket = new Socket("127.0.0.1",8888);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			Message initial = new Message("Client.Connect");
			initial.setArgument("username", "george");
			initial.setArgument("password", "secret");
			initial.setArgument("version", 1);
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
