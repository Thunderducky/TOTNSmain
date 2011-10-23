package grl.prototype.networking.server;

import java.util.Stack;

import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;

public class ChatProcessor extends MessageProcessorNode{
	private static final String messageType = "Chat";
	
	public ChatProcessor(){
		
	}
	
	@Override
	public String getMessageType() {
		return messageType;
	}
	
	class BroadcastMessageProcessor implements IMessageProcessor{

		@Override
		public String getMessageType() {
			return "Broadcast";
		}

		@Override
		public void processMessage(Stack<String> messageType, Message m) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
