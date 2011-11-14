package grl.prototype.example.client.messages.chat;

public class BroadcastChatMessage extends ChatMessage{
	public BroadcastChatMessage(String from, String messageText,long timestamp){
		super(from,messageText,timestamp);
	}
}
