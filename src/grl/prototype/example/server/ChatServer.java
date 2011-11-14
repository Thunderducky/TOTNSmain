package grl.prototype.example.server;

import grl.prototype.example.client.messages.chat.ChatStatusMessage;
import grl.prototype.example.client.messages.chat.ChatStatusMessage.Status;
import grl.prototype.networking.Server;
import grl.prototype.networking.server.ClientConnectionListener;

public class ChatServer {
	public static void main(String[] args){
		new ChatServer();
	}
	public ChatServer(){
		final Server server = new Server();
		server.setClientConnectionListener(new ClientConnectionListener(){

			@Override
			public void onClientConnect(String username) {
				server.sendMessageAll(new ChatStatusMessage(username,Status.Available,System.currentTimeMillis()));
			}

			@Override
			public void onClientDisconnect(String username) {
				server.sendMessageAll(new ChatStatusMessage(username,Status.Offline,System.currentTimeMillis()));
			}
			
		});
		server.start();
	}
}
