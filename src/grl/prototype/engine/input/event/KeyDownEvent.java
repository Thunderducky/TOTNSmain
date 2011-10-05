package grl.prototype.engine.input.event;

import grl.prototype.engine.Event;
import grl.prototype.engine.input.ButtonState;
import grl.prototype.engine.input.KeyboardView;

public class KeyDownEvent extends KeyEvent{

	public KeyDownEvent(int key, ButtonState state, KeyboardView view) {
		super(key, state, view);
	}
	
	
}
