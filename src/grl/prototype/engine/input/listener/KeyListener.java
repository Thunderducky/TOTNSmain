package grl.prototype.engine.input.listener;

import grl.prototype.engine.Listener;
import grl.prototype.engine.input.event.KeyEvent;
import grl.prototype.engine.input.event.KeyPressedEvent;

public interface KeyListener<T extends KeyEvent> extends Listener<KeyEvent>{
	public void onKeyEvent(T event);
	public boolean acceptsKey(int key);
}
