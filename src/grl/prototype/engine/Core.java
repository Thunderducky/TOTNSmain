package grl.prototype.engine;

import grl.prototype.engine.graphics.Graphics2D;
import grl.prototype.engine.input.InputDevices;

public class Core {
	private static InputDevices inputDevices = new InputDevices();
	private static Graphics2D graphics2D;
	
	public static void initialize(){
		inputDevices = new InputDevices();
		graphics2D = new Graphics2D();
	}
	
	public static InputDevices getInputDevices(){
		return inputDevices;
	}
	
	public static void update(long timeDelta){
		//input section
		inputDevices.update(timeDelta);
		
		//fire input events
		inputDevices.fireEvents();
		
		//draw
		graphics2D.update(timeDelta);
	}
}
