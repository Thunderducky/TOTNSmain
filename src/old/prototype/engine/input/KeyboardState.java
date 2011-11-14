package old.prototype.engine.input;
import java.util.*;

// Must initialize LWJGL before calling update

public class KeyboardState
{
	private Map<Integer, ButtonState> keyStates;
	
	public KeyboardState()
	{
		keyStates = new HashMap<Integer, ButtonState>();
	}
	public KeyboardState(Map<Integer,ButtonState> keyStates){
		this.keyStates = keyStates;
	}
	boolean IsKeyDown(int keyCode)
	{
		if(keyStates.containsKey(keyCode)){
			ButtonState state = keyStates.get(keyCode);
			return state == ButtonState.Pressed || state == ButtonState.Down ;
		}
		else
			return false;
	}
	public Map<Integer,ButtonState> getKeyStates(){
		return keyStates;
	}
}

