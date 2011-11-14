package grl.prototype.example.client.messages.chat;

import grl.prototype.messaging.Message;

public abstract class ChatMessage extends Message{
	private String from;
	private long timestamp;
	public ChatMessage(String from,long timestamp){
		this.from = from;
		this.timestamp = timestamp;
	}
	public String getFrom(){
		return from;
	}
	
	public String toString(){
		return from+": ";
	}
}
