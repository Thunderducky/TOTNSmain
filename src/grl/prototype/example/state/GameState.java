package grl.prototype.example.state;

import grl.prototype.example.state.chat.ChatState;
import grl.prototype.example.state.pong.PongState;
import grl.prototype.networking.client.Connection;
import grl.prototype.state.State;

public class GameState extends State{
	private ChatState chatState = new ChatState();
	private PongState pongState = new PongState();
	private Connection connection;
	
	public GameState(Connection connection){
		this.connection = connection;
	}
	public Connection getConnection(){
		return connection;
	}
	public ChatState getChatState(){
		return chatState;
	}
	public PongState getPongState(){
		return this.pongState;
	}
	public void setPongState(PongState state){
		this.pongState = state;
	}
	public void update(){
		pongState.update(this);
		chatState.update(this);
	}
}
