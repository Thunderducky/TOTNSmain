package eric.prototype;

import java.util.*;

import org.lwjgl.input.Keyboard;

class KeyboardController 
{
	
	InputDevicesSystemData data;
	
	KeyboardController(InputDevicesSystemData systemData)
	{
		data = systemData;
	}
	
	void Update()
	{
		data.oldKeyboardState.KeyStates = new HashMap<Integer, ButtonState>(data.newKeyboardState.KeyStates);
		while(Keyboard.next())
		{
			data.newKeyboardState.KeyStates.put(Keyboard.getEventKey(), Keyboard.getEventKeyState() ? ButtonState.Pressed : ButtonState.Released);
		}
	}
}
