package grl.prototype.example.client.messages.chat;

public class ChatStatusMessage extends ChatMessage {
	public enum Status{
		Available,
		Offline,
		Away
	}
	private Status status;
	public ChatStatusMessage(String from, Status status,long timestamp) {
		super(from, timestamp);
		this.status = status;
	}
	public String toString(){
		return this.getFrom()+" is "+status.toString();
	}
}
