package old.prototype.engine.input.listener;

import old.prototype.engine.Listener;
import old.prototype.engine.input.event.KeyEvent;
import old.prototype.engine.input.event.KeyPressedEvent;

public interface KeyListener<T extends KeyEvent> extends Listener<KeyEvent>{
	public void onKeyEvent(T event);
	public boolean acceptsKey(int key);
}
