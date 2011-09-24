package com.linearoja.fractals;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.linearoja.totns.Game;

public class Fractals extends Game{
	Fractal currentFractal = new Sierpinsky();
	float zoom = 4f;
	int x = 400;
	int y = 400;
	int recurseCount = 5;
	public static void main(String[] args){
		Fractals game = new Fractals();
		game.start();
	}
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void update(int delta) {
		
	}

	@Override
	protected void draw() {
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glPushMatrix();
		GL11.glTranslatef(400, 400, 0);
		GL11.glScalef(zoom, zoom, 1);
		
		currentFractal.draw(recurseCount);
		
		GL11.glPopMatrix();
	}
	@Override
	protected void pollInput(int delta) {
		int dwheel = Mouse.getDWheel();
		if(dwheel!=0){
			zoom *= 1+(((float)dwheel/120)*.25f);
			if(zoom < .01) zoom = .01f;
		}
		x = Mouse.getX();
		y = Mouse.getY();
		
		while(Keyboard.next()){
			if(!Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_1){
					currentFractal = new Sierpinsky();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_2){
					currentFractal = new Hangman();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_3){
					currentFractal = new Staircase();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_EQUALS){
					if(recurseCount < 15){
						recurseCount ++;
					}
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_MINUS){
					if(recurseCount >1){
						recurseCount --;
					}
				}
			}
		}
	}

}
