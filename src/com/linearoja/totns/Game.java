package com.linearoja.totns;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public abstract class Game {

	private Timer timer;

	protected Timer getTimer(){
		return timer;
	}
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setVSyncEnabled(true);
			Display.create();
			
			timer = new Timer();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL();
		while (!Display.isCloseRequested()) {
			int delta = timer.getDelta();
			pollInput(delta);
			update(delta);
			draw();
			Display.update();
			Display.sync(60);
			timer.updateFPS();
		}

		Display.destroy();
	}
	private void initGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	protected abstract void update(int delta);
	protected abstract void draw();
	protected abstract void pollInput(int delta);
}
