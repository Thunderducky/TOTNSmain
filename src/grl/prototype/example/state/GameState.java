package grl.prototype.example.state;

import grl.prototype.example.state.chat.ChatState;
import grl.prototype.networking.client.Connection;

public class GameState {
	private ChatState chatState = new ChatState();
	private long currentTime;
	private long deltaTime;
	private Connection connection;
	
	public GameState(Connection connection){
		this.connection = connection;
		update();
		deltaTime = 0;
	}
	public Connection getConnection(){
		return connection;
	}
	public ChatState getChatState(){
		return chatState;
	}
	public void update(){
		long newTime = System.currentTimeMillis();
		deltaTime = newTime - currentTime;
	}
	public long getTime(){
		return currentTime;
	}
	public long getTimeDelta(){
		return deltaTime;
	}
}
