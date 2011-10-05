package grl.prototype.engine.input;

class InputDevicesSystemData 
{
	KeyboardState oldKeyboardState;
	KeyboardState newKeyboardState;
	
	InputDevicesSystemData ()
	{
		oldKeyboardState = new KeyboardState();
		newKeyboardState = new KeyboardState();
	}
}
