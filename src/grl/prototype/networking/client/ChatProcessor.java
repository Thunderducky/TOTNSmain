package grl.prototype.networking.client;

import java.util.Stack;

import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.networking.Client;
import grl.prototype.networking.Server;

public class ChatProcessor extends MessageProcessorNode{
	private static final String messageType = "Chat";
	
	public ChatProcessor(){
		this.registerMessageProcessor(new BroadcastMessageProcessor());
	}
	
	@Override
	public String getMessageType() {
		return messageType;
	}
	

	public static Message createBroadcast(Client client,String message){
		Message messageObj = new Message(BroadcastMessageProcessor.messageType);
		messageObj.setArgument("from", client.getUsername());
		messageObj.setArgument("message", message);
		return messageObj;
	}
	
	class BroadcastMessageProcessor implements IMessageProcessor{
		private static final String messageType = ChatProcessor.messageType + ".Broadcast";
		@Override
		public String getMessageType() {
			return "Broadcast";
		}

		@Override
		public void processMessage(Stack<String> messageType, Message m) {
			String from = m.getArgumentString("from");
			String message = m.getArgumentString("message");
			System.out.println(from +": "+message);
		}
		
	}
}
