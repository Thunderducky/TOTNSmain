package grl.prototype.messaging;

import java.util.Stack;

public class RootMessageProcessorNode extends MessageProcessorNode{

	public void processMessage(Message m) throws MessageTypeException {
		Stack<String> typeStack = m.getTypeHierarchy();
		processMessage(typeStack,m);
	}

	@Override
	public String getMessageType() {
		return null;
	}

}
