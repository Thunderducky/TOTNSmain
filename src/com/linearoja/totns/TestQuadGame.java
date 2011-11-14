package com.linearoja.totns;

import grl.prototype.Game;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.newdawn.slick.TrueTypeFont;

import com.linearoja.Assets;

public class TestQuadGame extends Game{

	private float rotation;
	private float x = 300,y = 300;
	private static final float ROT_CONST = .1f;
	private static final float MOVE_CONST = .5f;
	
	@Override
protected void init() {
		// TODO Auto-generated method stub
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);	
		
		Assets.fonts.BleedingCowboys.loadData();
	}

	@Override
	protected void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.5f, 0.5f, 1.0f);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0, 0, 1);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(100, 100);
			GL11.glVertex2f(-100 , 100);
			GL11.glVertex2f(-100, -100);
			GL11.glVertex2f(100, -100);
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}

	@Override
	protected void pollInput(int delta) {

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

		while (Keyboard.next()) {}
	}
	
	public static void main(String[] args){
		TestQuadGame game = new TestQuadGame();
		game.start();
	}
	
}

