package grl.prototype.example.server;

import grl.prototype.example.client.messages.chat.ChatStatusMessage;
import grl.prototype.example.client.messages.chat.ChatStatusMessage.Status;
import grl.prototype.example.server.messages.ServerInMessageProcessor;
import grl.prototype.networking.Server;
import grl.prototype.networking.server.ClientConnectionListener;

public class ChatServer {
	public static void main(String[] args){
		new ChatServer();
	}
	public ChatServer(){
		final Server server = Server.getInstance();
		server.setClientConnectionListener(new ClientConnectionListener(){

			@Override
			public void onClientConnect(String username) {
				server.sendMessageAll(new ChatStatusMessage(username,Status.Available,System.currentTimeMillis()));
				System.out.println("Client Connected: "+username);
			}

			@Override
			public void onClientDisconnect(String username) {
				server.sendMessageAll(new ChatStatusMessage(username,Status.Offline,System.currentTimeMillis()));
				System.out.println("Client Disconnected: "+username);
			}
			
		});
		server.setRootMessageProcessor(new ServerInMessageProcessor(server));
		new ServerConsole(server);
		server.start();
	}
}
