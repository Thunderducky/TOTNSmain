package grl.prototype.example.client.messages.pong;

import grl.prototype.example.state.pong.Paddle;
import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;

public class StartGameMessage extends PongStateMessage{

	public StartGameMessage(String username,PongState state) {
		super(username,state);
	}
}
