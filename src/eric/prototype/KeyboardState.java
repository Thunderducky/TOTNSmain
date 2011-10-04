package eric.prototype;
import java.util.*;

import org.lwjgl.input.Keyboard;

// Must initialize LWJGL before calling update

public class KeyboardState
{
	public Map<Integer, ButtonState> KeyStates;
	
	public KeyboardState()
	{
		KeyStates = new HashMap<Integer, ButtonState>();
	}
}

