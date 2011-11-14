package old.prototype.engine.input.event;

import old.prototype.engine.Event;
import old.prototype.engine.input.ButtonState;
import old.prototype.engine.input.KeyboardView;

public class KeyEvent extends Event<KeyboardView>{
	private int key;
	private KeyboardView view;
	private ButtonState state;
	
	public KeyEvent(int key,ButtonState state,KeyboardView view){
		this.key = key;
		this.state = state;
		this.view = view;
	}
	
	public int getKey(){
		return key;
	}
	public ButtonState getState(){
		return state;
	}
	
	@Override
	public KeyboardView getView() {
		return view;
	}
}
