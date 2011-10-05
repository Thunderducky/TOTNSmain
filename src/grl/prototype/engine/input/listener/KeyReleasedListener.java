package grl.prototype.engine.input.listener;

import grl.prototype.engine.Listener;
import grl.prototype.engine.input.event.KeyReleasedEvent;

public interface KeyReleasedListener extends KeyListener<KeyReleasedEvent>{
	public void onKeyEvent(KeyReleasedEvent event);
	public boolean acceptsKey(int key);
}
