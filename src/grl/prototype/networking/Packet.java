package grl.prototype.networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grl.prototype.messaging.Message;

public class Packet implements Serializable {
	Message[] messages = new Message[0];
	public Packet(Message... messages){
		this.messages = messages;
	}
	public List<Message> getMessages(){
		return Arrays.asList(messages);
	}
	public boolean hasMessages(){
		return messages.length > 0;
	}
}
