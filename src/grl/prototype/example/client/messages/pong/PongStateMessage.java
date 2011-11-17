package grl.prototype.example.client.messages.pong;

import grl.prototype.example.state.pong.PongState;
import grl.prototype.messaging.Message;

public class PongStateMessage extends Message{
	private String username;
	private final PongState state;
	public PongStateMessage(String username,PongState state){
		this.state = state;
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public PongState getState(){
		return state;
	}
}
