package com.renderer;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class KeyBoardHandler {

	private ArrayList<Key> keyHandlers = new ArrayList<Key>();
	
	private class Key 
	{
		public Key(int keyId, KeyBoardKeyHandler handler)
		{
			this.keyId = keyId;
			this.handler = handler;
		}
		
		private int keyId;
		private KeyBoardKeyHandler handler;
		private boolean wasPressed = false;
		
		public KeyBoardKeyHandler getHandler() {
			return handler;
		}
		public int getKeyId() {
			return keyId;
		}
		public boolean isWasPressed() {
			return wasPressed;
		}
		public void setWasPressed(boolean wasPressed) {
			this.wasPressed = wasPressed;
		}
		
		
	}
	
	public KeyBoardHandler()
	{
		addKey(Keyboard.KEY_O, new keyOpenOBJFile());
	}
	
	public void addKey(int keyId, KeyBoardKeyHandler handler)
	{
		this.keyHandlers.add(new Key(keyId, handler));
	}
	
	public void update()
	{
		for (Key key: keyHandlers)
		{
			if (Keyboard.isKeyDown(key.getKeyId()))
			{
				if (!key.isWasPressed())
				{
					key.handler.onKeyPressed();
					key.setWasPressed(true);
				}
				
			}
			else
				key.setWasPressed(false);
		}
	}
}
