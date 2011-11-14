package grl.prototype.example.client.messages.chat;

import grl.prototype.messaging.Message;

public abstract class ChatMessage extends Message{
	private String from;
	private String messageText;
	private long timestamp;
	public ChatMessage(String from, String messageText, long timestamp){
		this.from = from;
		this.messageText = messageText;
		this.timestamp = timestamp;
	}
	public String getText(){
		return messageText;
	}
	public String getFrom(){
		return from;
	}
	
	
}
