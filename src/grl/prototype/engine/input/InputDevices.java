package grl.prototype.engine.input;

import grl.prototype.engine.Controller;

public class InputDevices implements Controller{
	
	private Keyboard keyboard;
	
	public InputDevices(){
		keyboard = new Keyboard();
	}
	
	public Keyboard getKeyboard(){
		return keyboard;
	}
	public void update(long timeDelta){
		keyboard.update(timeDelta);
	}
	
	@Override
	public void fireEvents() {
		keyboard.fireEvents();
	}
	
}
