package grl.prototype.example.client.messages.chat;

public class BroadcastChatMessage extends ChatMessage{
	private String messageText;
	public BroadcastChatMessage(String from, String messageText,long timestamp){
		super(from,timestamp);
		this.messageText = messageText;
	}
	public String getText(){
		return messageText;
	}
	public String toString(){
		return super.toString()+messageText;
	}
}
