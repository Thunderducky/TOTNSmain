package grl.prototype.example.client.messages.pong;

import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;

public class EndGameMessage extends PongStateMessage{

	public EndGameMessage(String username,PongState state) {
		super(username,state);
		// TODO Auto-generated constructor stub
	}
}
