package grl.prototype.messaging.example;

import java.util.Stack;

import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.MessageTypeException;

public class InputProcessor extends MessageProcessorNode{

	public InputProcessor(){
		this.registerMessageProcessor(new KeyEventProcessor());
	}
	@Override
	public String getMessageType() {
		return "InputEvent";
	}
	
	class KeyEventProcessor implements IMessageProcessor{

		@Override
		public String getMessageType() {
			return "KeyEvent";
		}

		@Override
		public void processMessage(Stack<String> messageType, Message m)
				throws MessageTypeException {
			String type = messageType.pop();
			System.out.println(type+" keycode "+((KeyEvent)m).keycode);
			
		}
		
	}
}

