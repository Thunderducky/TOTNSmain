package grl.prototype.messaging.example;

import grl.prototype.messaging.Message;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.RootMessageProcessorNode;

import java.util.Stack;

public class Example {
	public static void main(String[] args){
		MessageProcessorNode root = new RootMessageProcessorNode();
		root.registerMessageProcessor(new InputProcessor());
		root.processMessage(new KeyDownEvent(10));
		root.processMessage(new KeyUpEvent(15));
	}
}



