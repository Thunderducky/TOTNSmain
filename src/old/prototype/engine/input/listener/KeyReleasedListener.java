package old.prototype.engine.input.listener;

import old.prototype.engine.Listener;
import old.prototype.engine.input.event.KeyReleasedEvent;

public interface KeyReleasedListener extends KeyListener<KeyReleasedEvent>{
	public void onKeyEvent(KeyReleasedEvent event);
	public boolean acceptsKey(int key);
}
