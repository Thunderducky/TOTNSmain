package grl.prototype.messaging.example;

import java.util.Stack;

public class KeyEvent extends InputEvent{
	int keycode;
	KeyEvent(int keycode){
		this.keycode = keycode;
	}
	@Override
	public Stack<String> getTypeHierarchy() {
		Stack<String> stack = super.getTypeHierarchy();
		stack.insertElementAt("KeyEvent", 0);
		return stack;
	}
}
