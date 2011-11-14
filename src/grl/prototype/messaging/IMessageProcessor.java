package grl.prototype.messaging;

import java.util.List;
import java.util.Stack;

public interface IMessageProcessor {
	public List<Class> getAcceptedMessageTypes();
	public boolean processMessage(Message m);
}
