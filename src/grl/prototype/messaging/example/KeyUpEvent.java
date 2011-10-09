package grl.prototype.messaging.example;

import java.util.Stack;

public class KeyUpEvent extends KeyEvent{

	KeyUpEvent(int keycode) {
		super(keycode);
	}
	@Override
	public Stack<String> getTypeHierarchy() {
		Stack<String> stack = super.getTypeHierarchy();
		stack.insertElementAt("KeyUpEvent", 0);
		return stack;
	}
}
