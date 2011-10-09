package grl.prototype.messaging;

import java.util.Stack;

public interface IMessageProcessor {
	public String getMessageType();
	public void processMessage(Stack<String> messageType,Message m);
}
