package grl.prototype.example.server.messages.chat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import grl.prototype.example.client.messages.chat.BroadcastChatMessage;
import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.networking.NetworkClient;
import grl.prototype.networking.Server;

public class ChatInProcessor extends MessageProcessorNode{
	private Server server;
	public ChatInProcessor(Server server){
		this.registerMessageProcessor(new BroadcastMessageProcessor());
		this.server =server;
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
			server.sendMessageAll(broadcast);
			return true;
		}
		
	}
}
