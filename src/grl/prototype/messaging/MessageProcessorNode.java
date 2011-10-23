package grl.prototype.messaging;

import java.util.HashMap;
import java.util.Stack;

public abstract class MessageProcessorNode implements IMessageProcessor{
	private HashMap<String,IMessageProcessor> processors = new HashMap<String,IMessageProcessor>();


	@Override
	public abstract String getMessageType();
	
	public void registerMessageProcessor(IMessageProcessor processor){
		processors.put(processor.getMessageType(), processor);
	}

	@Override
	public void processMessage(Stack<String> typeStack, Message m){
		String type = typeStack.pop();
		IMessageProcessor processor = processors.get(type);
		if(processor!=null){
			processor.processMessage(typeStack, m);
		}
		else{
			throw new MessageTypeException("Message type:'"+type+"' is unregistered");
		}
	}
}
