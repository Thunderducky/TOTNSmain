package grl.prototype.messaging.example;

import java.util.Stack;

public class KeyDownEvent extends KeyEvent{

	KeyDownEvent(int keycode) {
		super(keycode);
	}
	@Override
	public Stack<String> getTypeHierarchy() {
		Stack<String> stack = super.getTypeHierarchy();
		stack.insertElementAt("KeyDownEvent", 0);
		return stack;
	}
	
}