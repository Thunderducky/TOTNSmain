package old.prototype.engine.input;

import old.prototype.engine.View;

public class KeyboardView extends View{

	KeyboardState oldKeys;
	KeyboardState newKeys;
	KeyboardView(KeyboardState oldKeys, KeyboardState newKeys)
	{
		this.oldKeys = oldKeys;
		this.newKeys = newKeys;
	}
	
	public boolean isKeyPressed(int keyCode)
	{
		return !oldKeys.IsKeyDown(keyCode) && newKeys.IsKeyDown(keyCode);
	}
	public boolean isKeyReleased(int keyCode)
	{
		return oldKeys.IsKeyDown(keyCode) && !newKeys.IsKeyDown(keyCode);
	}
	public boolean isKeyDown(int keyCode)
	{
		return newKeys.IsKeyDown(keyCode);
	}
	
}


