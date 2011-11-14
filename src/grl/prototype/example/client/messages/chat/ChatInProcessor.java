package grl.prototype.example.client.messages.chat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import grl.prototype.example.state.chat.ChatState;
import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.networking.NetworkClient;
import grl.prototype.networking.Server;

public class ChatInProcessor extends MessageProcessorNode{
	ChatState chatState;
	public ChatInProcessor(ChatState state){
		this.registerMessageProcessor(new BroadcastMessageProcessor());
		chatState = state;
	}
	
	class BroadcastMessageProcessor implements IMessageProcessor{
		private List<Class> acceptedMessageTypes;
		{
			LinkedList<Class> types = new LinkedList<Class>();
			types.add(BroadcastChatMessage.class);
			acceptedMessageTypes = Collections.unmodifiableList(types);
		}
		
		@Override
		public List<Class> getAcceptedMessageTypes() {
			return acceptedMessageTypes;
		}

		@Override
		public boolean processMessage(Message m) {
			BroadcastChatMessage broadcast = (BroadcastChatMessage)m;
			chatState.addMessage(broadcast);
			return true;
		}
		
	}
}
