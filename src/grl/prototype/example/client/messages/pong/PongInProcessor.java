package grl.prototype.example.client.messages.pong;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import grl.prototype.example.client.messages.chat.BroadcastChatMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;

public class PongInProcessor implements IMessageProcessor{
	private GameState state;
	private List<Class> acceptedMessageTypes;
	{
		LinkedList<Class> types = new LinkedList<Class>();
		types.add(StartGameMessage.class);
		types.add(EndGameMessage.class);
		types.add(PongStateMessage.class);
		acceptedMessageTypes = Collections.unmodifiableList(types);
	}
	public PongInProcessor(GameState state){
		this.state = state;
	}

	@Override
	public List<Class> getAcceptedMessageTypes() {
		return acceptedMessageTypes;
	}

	@Override
	public boolean processMessage(Message m) {
		state.setPongState(((PongStateMessage)m).getState());
		return true;
	}
	
	
}
