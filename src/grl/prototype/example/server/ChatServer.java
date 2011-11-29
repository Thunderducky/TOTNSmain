package grl.prototype.example.server;

import grl.prototype.example.client.messages.chat.ChatStatusMessage;
import grl.prototype.example.client.messages.chat.ChatStatusMessage.Status;
import grl.prototype.example.client.messages.pong.PongStateMessage;
import grl.prototype.example.server.messages.ServerInMessageProcessor;
import grl.prototype.example.state.GameState;
import grl.prototype.networking.Server;
import grl.prototype.networking.server.ClientConnectionListener;

public class ChatServer extends Thread{
	private GameState state;
	final Server server;
	private ServerInMessageProcessor inProcessor;
	public static void main(String[] args){
		new ChatServer().start();
	}
	public ChatServer(){
		state = new GameState(null);
		server = Server.getInstance();
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
		inProcessor = new ServerInMessageProcessor(server,state);
		server.setRootMessageProcessor(inProcessor);
		new ServerConsole(server);
		server.start();
	}
	@Override
	public void run(){
		while(server.isAlive()){
			state.updateTime();
			state.update();
			inProcessor.dispatchMessages();
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
