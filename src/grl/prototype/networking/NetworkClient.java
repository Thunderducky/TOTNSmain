package grl.prototype.networking;

import grl.prototype.example.client.messages.ClientInMessageProcessor;
import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.RootMessageProcessor;
import grl.prototype.networking.client.Connection;
import grl.prototype.networking.client.messages.ConnectMessage;
import grl.prototype.networking.client.messages.DisconnectMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class NetworkClient{
	private static final int VERSION = 1;
	private NetworkClientThread client;
	private Connection connection;
	public NetworkClient(Connection connection, RootMessageProcessor messageProcessor){
		this.connection = connection;
		client = new NetworkClientThread(messageProcessor);
		client.start();
	}
	public synchronized void stop(){
		client.disconnect();
	}
	public synchronized void sendMessage(Message message){
		client.sendMessage(message);
	}
	public Connection getConnection(){
		return connection;
	}

	class NetworkClientThread extends Thread{
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Queue<Message> messageQueue = new LinkedList<Message>();
		boolean stop = false;
		RootMessageProcessor processor;
		NetworkClientThread(RootMessageProcessor processor){
			super();
			this.processor = processor;
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
				Socket socket = new Socket(NetworkClient.this.connection.getServerAddress()
						,NetworkClient.this.connection.getSocket());
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());

				ConnectMessage initial = new ConnectMessage(NetworkClient.this.connection,
						NetworkClient.VERSION);
				
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
						processor.addMessage(message);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				oos.writeObject(new Packet(new DisconnectMessage(NetworkClient.this.connection,
						NetworkClient.VERSION
						)));
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


