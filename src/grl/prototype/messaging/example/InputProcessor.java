package grl.prototype.messaging.example;

import java.util.Stack;

import grl.prototype.messaging.IMessageProcessor;
import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.MessageTypeException;

public class InputProcessor extends MessageProcessorNode{
	public static final String KEY_DOWN_EVENT = "Input.Key.Down", KEY_UP_EVENT = "Input.Key.Up";
	
	
	public InputProcessor(){
		this.registerMessageProcessor(new KeyEventProcessor());
	}
	@Override
	public String getMessageType() {
		return "Input";
	}
	
	public static Message createKeyDownMessage(int keycode){
		Message message = new Message(KEY_DOWN_EVENT);
		message.setArgument("keycode", keycode);
		return message;
	}
	public static Message createKeyUpMessage(int keycode){
		Message message = new Message(KEY_UP_EVENT);
		message.setArgument("keycode", keycode);
		return message;
	}
	
	class KeyEventProcessor implements IMessageProcessor{

		@Override
		public String getMessageType() {
			return "Key";
		}

		@Override
		public void processMessage(Stack<String> messageType, Message m)
				throws MessageTypeException {
			String type = messageType.pop();
			System.out.println(type+" keycode "+m.getArgumentInt("keycode"));
			
		}
		
	}
}

