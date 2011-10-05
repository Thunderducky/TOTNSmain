package grl.prototype.engine.input.event;

import grl.prototype.engine.input.ButtonState;
import grl.prototype.engine.input.KeyboardView;

public class KeyPressedEvent extends KeyDownEvent{

	public KeyPressedEvent(int key, ButtonState state, KeyboardView view) {
		super(key, state, view);
	}

}
