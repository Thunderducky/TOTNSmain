package grl.prototype.engine.input.event;

import grl.prototype.engine.Event;
import grl.prototype.engine.input.ButtonState;
import grl.prototype.engine.input.KeyboardView;

public class KeyReleasedEvent extends KeyEvent{

	public KeyReleasedEvent(int key, ButtonState state, KeyboardView view) {
		super(key, state, view);
	}
	
	
}
