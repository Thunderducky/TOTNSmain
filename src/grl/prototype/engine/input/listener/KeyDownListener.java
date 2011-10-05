package grl.prototype.engine.input.listener;

import grl.prototype.engine.Listener;
import grl.prototype.engine.input.event.KeyDownEvent;

public interface KeyDownListener extends KeyListener<KeyDownEvent>{
	public void onKeyEvent(KeyDownEvent event);
	public boolean acceptsKey(int key);
}
