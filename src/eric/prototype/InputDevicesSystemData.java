package eric.prototype;

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
