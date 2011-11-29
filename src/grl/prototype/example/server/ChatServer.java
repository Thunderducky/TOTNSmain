package grl.prototype.example.server;

import java.util.Collection;
import java.util.List;

import grl.prototype.example.client.messages.chat.ChatStatusMessage;
import grl.prototype.example.client.messages.chat.ChatStatusMessage.Status;
import grl.prototype.example.client.messages.pong.EndGameMessage;
import grl.prototype.example.client.messages.pong.PongStateMessage;
import grl.prototype.example.client.messages.pong.StartGameMessage;
import grl.prototype.example.server.messages.ServerInMessageProcessor;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;
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
		int updatesReceived = 0;
		while(server.isAlive()){
			
			updatesReceived += inProcessor.getMessageCount();
			inProcessor.dispatchMessages();
			List<String> users = server.getConnectedUsers();
			if(users.size()>=2){
				if(!state.getPongState().gameIsStarted()){
					Player p1 = new Player(users.get(0),Player.Number.One);
					Player p2 = new Player(users.get(1),Player.Number.Two);
					state.setPongState(new PongState(p1,p2));
					state.getPongState().startGame();
					System.out.println("begin!");
					server.sendMessageAll(new StartGameMessage(null,state.getPongState()));
				}
				if(state.getPongState().gameIsComplete()){
					state.getPongState().stopGame();
					server.sendMessageAll(new EndGameMessage(null,state.getPongState()));
				}
			}
			else if(users.size()<2){
				if(state.getPongState().gameIsStarted()){
					state.getPongState().stopGame();
					server.sendMessageAll(new EndGameMessage(null,state.getPongState()));
				}
			}
			state.updateTime();
			state.update();
			if(state.getPongState().gameIsStarted())
				server.sendMessageAll(new PongStateMessage(null,state.getPongState()));
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
