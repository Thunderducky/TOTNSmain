package eric.prototype;

public class KeyboardView {

	KeyboardHelper oldKeys;
	KeyboardHelper newKeys;
	KeyboardView(InputDevicesSystemData systemdata)
	{
		oldKeys = new KeyboardHelper(systemdata.oldKeyboardState);
		newKeys = new KeyboardHelper(systemdata.newKeyboardState);
	}
	
	public boolean IsNewKeyPress(int keyCode)
	{
		return !oldKeys.IsKeyDown(keyCode) && newKeys.IsKeyDown(keyCode);
	}
	public boolean IsNewKeyRelease(int keyCode)
	{
		return oldKeys.IsKeyDown(keyCode) && !newKeys.IsKeyDown(keyCode);
	}
	public boolean IsKeyPressed(int keyCode)
	{
		return newKeys.IsKeyDown(keyCode);
	}
	
	class KeyboardHelper
	{
		KeyboardState keys;
		
		KeyboardHelper(KeyboardState state)
		{
			keys = state;
		}
		
		boolean IsKeyDown(int keyCode)
		{
			if(keys.KeyStates.containsKey(keyCode))
				return keys.KeyStates.get(keyCode) == ButtonState.Pressed;
			else
				return false;
		}
	}
	
}


