package old.prototype.engine.input.listener;

import old.prototype.engine.Listener;
import old.prototype.engine.input.event.KeyPressedEvent;

public interface KeyPressedListener extends KeyListener<KeyPressedEvent>{
	public void onKeyEvent(KeyPressedEvent event);
	public boolean acceptsKey(int key);
}
