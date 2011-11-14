package old.prototype.engine.input.event;

import old.prototype.engine.Event;
import old.prototype.engine.input.ButtonState;
import old.prototype.engine.input.KeyboardView;

public class KeyDownEvent extends KeyEvent{

	public KeyDownEvent(int key, ButtonState state, KeyboardView view) {
		super(key, state, view);
	}
	
	
}
