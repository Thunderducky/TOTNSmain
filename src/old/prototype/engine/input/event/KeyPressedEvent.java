package old.prototype.engine.input.event;

import old.prototype.engine.input.ButtonState;
import old.prototype.engine.input.KeyboardView;

public class KeyPressedEvent extends KeyDownEvent{

	public KeyPressedEvent(int key, ButtonState state, KeyboardView view) {
		super(key, state, view);
	}

}
