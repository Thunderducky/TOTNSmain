package grl.prototype.example.server.messages.pong;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import grl.prototype.example.client.messages.chat.BroadcastChatMessage;
import grl.prototype.example.client.messages.pong.PongStateMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.pong.Paddle;
import grl.prototype.example.state.pong.Player;
import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.networking.Server;

public class PongInProcessor implements IMessageProcessor{
	Server server;
	GameState state;
	public PongInProcessor(GameState state){
		this.state = state;
	}
	
	private List<Class> acceptedMessageTypes;
	{
		LinkedList<Class> types = new LinkedList<Class>();
		types.add(PongStateMessage.class);
		acceptedMessageTypes = Collections.unmodifiableList(types);
	}
	
	@Override
	public List<Class> getAcceptedMessageTypes() {
		return acceptedMessageTypes;
	}
	@Override
	public boolean processMessage(Message m) {
		PongStateMessage message = (PongStateMessage)m;
		Player oldPlayer = null;
		if((oldPlayer = this.state.getPongState().getPlayer(message.getUsername()))!=null){
			Player newPlayer = message.getState().getPlayer(message.getUsername());
			if(newPlayer!=null && newPlayer.getNumber().equals(oldPlayer.getNumber())){
				Paddle oldPaddle = oldPlayer.getPaddle();
				oldPaddle.copyValues( newPlayer.getPaddle());
			}
		}
		return true;
	}

}
