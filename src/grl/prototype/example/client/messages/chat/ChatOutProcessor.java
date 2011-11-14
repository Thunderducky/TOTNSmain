package grl.prototype.example.client.messages.chat;

import grl.prototype.example.client.messages.chat.ChatInProcessor.BroadcastMessageProcessor;
import grl.prototype.example.state.chat.ChatState;
import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.networking.NetworkClient;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ChatOutProcessor extends MessageProcessorNode{
	ChatState chatState;
	NetworkClient client;
	public ChatOutProcessor(ChatState state,NetworkClient client){
		this.registerMessageProcessor(new BroadcastMessageProcessor());
		chatState = state;
		this.client = client;
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
			client.sendMessage(m);
			return true;
		}
		
	}

}
