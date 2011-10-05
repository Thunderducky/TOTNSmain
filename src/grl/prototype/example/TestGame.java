package grl.prototype.example;

import grl.prototype.engine.Core;
import grl.prototype.engine.Game;
import grl.prototype.engine.input.Keyboard;
import grl.prototype.engine.input.event.KeyDownEvent;
import grl.prototype.engine.input.event.KeyPressedEvent;
import grl.prototype.engine.input.event.KeyReleasedEvent;
import grl.prototype.engine.input.listener.KeyDownListener;
import grl.prototype.engine.input.listener.KeyPressedListener;
import grl.prototype.engine.input.listener.KeyReleasedListener;

public class TestGame extends Game {
	public static void main(String[] args){
		TestGame tg = new TestGame();
		tg.start();
	}
	@Override
	public void init(){
		super.init();
		Keyboard keyboard = Core.getInputDevices().getKeyboard();
		keyboard.addKeyDownListener(new KeyDownListener(){
			@Override
			public void onKeyEvent(KeyDownEvent event) {
				System.out.println("Key + is down");
			}

			@Override
			public boolean acceptsKey(int key) {
				return key == org.lwjgl.input.Keyboard.KEY_EQUALS;
			}
			
		});
		keyboard.addKeyPressedListener(new KeyPressedListener(){

			@Override
			public void onKeyEvent(KeyPressedEvent event) {
				System.out.println("Pressed down minus key");
			}

			@Override
			public boolean acceptsKey(int key) {
				return key == org.lwjgl.input.Keyboard.KEY_MINUS;
			}
			
		});
		keyboard.addKeyReleasedListener(new KeyReleasedListener(){

			@Override
			public void onKeyEvent(KeyReleasedEvent event) {
				System.out.println("Any key released");
			}

			@Override
			public boolean acceptsKey(int key) {
				return true;
			}
			
		});
	}
}
