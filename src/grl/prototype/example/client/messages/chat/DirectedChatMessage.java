package grl.prototype.example.client.messages.chat;

public class DirectedChatMessage  extends ChatMessage{
	private String to;
	public DirectedChatMessage(String from, String to,String messageText, long timestamp) {
		super(from, messageText,timestamp);
		this.to = to;
	}
	public String getTo(){
		return to;
	}

}
