package old.prototype.engine.input.listener;

import old.prototype.engine.Listener;
import old.prototype.engine.input.event.KeyDownEvent;

public interface KeyDownListener extends KeyListener<KeyDownEvent>{
	public void onKeyEvent(KeyDownEvent event);
	public boolean acceptsKey(int key);
}
