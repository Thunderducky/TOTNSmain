package grl.prototype.engine.input.event;

import grl.prototype.engine.Event;
import grl.prototype.engine.input.ButtonState;
import grl.prototype.engine.input.KeyboardView;

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
