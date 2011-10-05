package grl.prototype.engine.input.listener;

import grl.prototype.engine.Listener;
import grl.prototype.engine.input.event.KeyPressedEvent;

public interface KeyPressedListener extends KeyListener<KeyPressedEvent>{
	public void onKeyEvent(KeyPressedEvent event);
	public boolean acceptsKey(int key);
}
