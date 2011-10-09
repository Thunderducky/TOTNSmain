package grl.prototype.messaging.example;

import grl.prototype.messaging.Message;

import java.util.Stack;

public class InputEvent extends Message{

	@Override
	public Stack<String> getTypeHierarchy() {
		Stack<String> stack = new Stack<String>();
		stack.insertElementAt("InputEvent", 0);
		return stack;
	}
	
}