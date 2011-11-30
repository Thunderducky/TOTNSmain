package grl.prototype.networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import grl.prototype.messaging.Message;

public class Packet implements Serializable {
	Message[] messages = new Message[0];
	public Packet(Message... messages){
		this.messages = messages;
	}
	public Packet(Collection<Message> messages){
		synchronized(messages){
			this.messages = messages.toArray(this.messages);
		}
	}
	public List<Message> getMessages(){
		return Arrays.asList(messages);
	}
	public boolean hasMessages(){
		return messages.length > 0;
	}
}
