package grl.prototype.messaging;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class MessageProcessorNode implements IMessageProcessor{
	private HashMap<Class,IMessageProcessor> processors = new HashMap<Class,IMessageProcessor>();
	private List<Class> acceptedMessageTypes;

	@Override
	public List<Class> getAcceptedMessageTypes(){
		return acceptedMessageTypes;
	}
	
	public void registerMessageProcessor(IMessageProcessor processor){
		List<Class> types = processor.getAcceptedMessageTypes();
		for(Class type:types){
			processors.put(type, processor);
		}
		acceptedMessageTypes = Collections.unmodifiableList(new LinkedList(processors.keySet()));
	}

	@Override
	public boolean processMessage(Message m){
		IMessageProcessor processor = processors.get(m.getClass());
		if(processor!=null){
			return processor.processMessage(m);
		}
		else{
			throw new MessageTypeException("Message type:'"+m.getClass().getCanonicalName()+"' is unregistered");
		}
	}
}
