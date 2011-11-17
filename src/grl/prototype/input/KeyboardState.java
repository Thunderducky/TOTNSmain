package grl.prototype.input;
import grl.prototype.state.State;
import grl.prototype.state.Updateable;

import java.util.*;

// Must initialize LWJGL before calling update

public class KeyboardState implements Updateable<State>
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
	
	public void update(State gameState){
		
	}
}

