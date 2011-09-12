package com.linearoja.totns;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
 

public class RotatingSquareGame extends Game{
	public static void main(String[] args){
		RotatingSquareGame game = new RotatingSquareGame();
		game.start();
	}
	

	private float rotation;
	private float x = 300,y = 300;
	private static final float ROT_CONST = .1f;
	private static final float MOVE_CONST = .5f;

	@Override
	public void draw(){
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);

		// draw quad
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0, 0, 1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(-50,-50);
		GL11.glVertex2f(50,-50);
		GL11.glVertex2f(50,50);
		GL11.glVertex2f(-50,50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	@Override
	public void pollInput(int delta){

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			rotation += (float)delta*ROT_CONST;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			rotation -= (float)delta*ROT_CONST;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			y -= (float)delta*MOVE_CONST;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			y += (float)delta*MOVE_CONST;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x -= (float)delta*MOVE_CONST;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x += (float)delta*MOVE_CONST;
		}
		
		
		
		while (Keyboard.next()) {
			
		}
	}
	@Override
	public void update(int delta) {
		Display.setTitle("FPS: "+getTimer().getFPS());
	}
}
