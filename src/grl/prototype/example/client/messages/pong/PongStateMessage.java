package grl.prototype.example.client.messages.pong;

import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;
import grl.prototype.messaging.Message;

public class PongStateMessage extends Message{
	private String username;
	private PongState state;
	public PongStateMessage(String username,PongState state){
		//System.out.println(state.getPlayer(Player.Number.One).getPaddle().getY());
		this.state = new PongState(state);
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public PongState getState(){
		return state;
	}
}
