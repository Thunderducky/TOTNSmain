package old.prototype.engine.input;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import old.prototype.engine.Controller;
import old.prototype.engine.input.event.KeyDownEvent;
import old.prototype.engine.input.event.KeyPressedEvent;
import old.prototype.engine.input.event.KeyReleasedEvent;
import old.prototype.engine.input.listener.KeyDownListener;
import old.prototype.engine.input.listener.KeyPressedListener;
import old.prototype.engine.input.listener.KeyReleasedListener;


public class Keyboard implements Controller{
	private Vector<KeyReleasedListener> keyReleasedListeners = new Vector<KeyReleasedListener>();
	private Vector<KeyDownListener> keyDownListeners = new Vector<KeyDownListener>();
	private Vector<KeyPressedListener> keyPressedListeners = new Vector<KeyPressedListener>();
	private Vector<KeyPressedEvent> keyPressedEvents = new Vector<KeyPressedEvent>();
	private Vector<KeyReleasedEvent> keyReleasedEvents = new Vector<KeyReleasedEvent>();
	private Vector<KeyDownEvent> keyDownEvents = new Vector<KeyDownEvent>();


	KeyboardState oldKeyboardState = new KeyboardState();
	KeyboardState newKeyboardState = new KeyboardState();

	public void addKeyPressedListener(KeyPressedListener listener){
		keyPressedListeners.add(listener);
	}
	public void addKeyDownListener(KeyDownListener listener){
		keyDownListeners.add(listener);
	}

	public void addKeyReleasedListener(KeyReleasedListener listener){
		keyReleasedListeners.add(listener);
	}

	public void update(long timeDelta)
	{
		oldKeyboardState = newKeyboardState;
		newKeyboardState = new KeyboardState();

		Map<Integer,ButtonState> currentKeyStates = new HashMap<Integer,ButtonState>();
		while(org.lwjgl.input.Keyboard.next())
		{
			int key = org.lwjgl.input.Keyboard.getEventKey();
			ButtonState state;
			if(org.lwjgl.input.Keyboard.getEventKeyState()){
				state = ButtonState.Pressed;
				//System.out.println("key "+key+" pressed");
			}
			else{
				state = ButtonState.Released;
				//System.out.println("key "+key+" released");
			}
			currentKeyStates.put(key, state);
		}

		for(int key:oldKeyboardState.getKeyStates().keySet()){
			if(!currentKeyStates.containsKey(key)){
				if(oldKeyboardState.IsKeyDown(key)){
					//The key must still be down. Good to know
					//System.out.println("key "+key+" still down");
					currentKeyStates.put(key, ButtonState.Down);
				}
			}
		}

		newKeyboardState = new KeyboardState(currentKeyStates);
		KeyboardView view = new KeyboardView(oldKeyboardState,newKeyboardState);

		//Ready to turn these into events
		for(int key:currentKeyStates.keySet()){
			ButtonState state = currentKeyStates.get(key);
			switch(state){
			case Down:
				keyDownEvents.add(new KeyDownEvent(key,state,view));
				break;
			case Pressed:
				keyPressedEvents.add(new KeyPressedEvent(key,state,view));
				keyDownEvents.add(new KeyDownEvent(key,state,view));
				break;
			case Released:
				keyReleasedEvents.add(new KeyReleasedEvent(key,state,view));
				break;
			}
		}



	}

	@Override
	public void fireEvents() {
		for(KeyDownEvent event:keyDownEvents){
			for(KeyDownListener listener: keyDownListeners){
				if(listener.acceptsKey(event.getKey())){
					listener.onKeyEvent(event);
				}
			}
		}
		keyDownEvents.clear();
		for(KeyReleasedEvent event:keyReleasedEvents){
			for(KeyReleasedListener listener: keyReleasedListeners){
				if(listener.acceptsKey(event.getKey())){
					listener.onKeyEvent(event);
				}
			}
		}
		keyReleasedEvents.clear();
		for(KeyPressedEvent event:keyPressedEvents){
			for(KeyPressedListener listener: keyPressedListeners){
				if(listener.acceptsKey(event.getKey())){
					listener.onKeyEvent(event);
				}
			}
		}
		keyPressedEvents.clear();
	}

}
