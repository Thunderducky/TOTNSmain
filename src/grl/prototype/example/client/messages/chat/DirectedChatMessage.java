package grl.prototype.example.client.messages.chat;

public class DirectedChatMessage  extends ChatMessage{
	private String to,messageText;
	public DirectedChatMessage(String from, String to,String messageText, long timestamp) {
		super(from,timestamp);
		this.to = to;
		this.messageText = messageText;
	}
	public String getText(){
		return messageText;
	}
	public String getTo(){
		return to;
	}
	public String toString(){
		return super.toString()+messageText;
	}
}
