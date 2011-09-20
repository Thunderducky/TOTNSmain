package com.linearoja.totns;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.linearoja.Assets;


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
	public void init(){

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT1);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        
		
		FloatBuffer lightAmbient = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.5f, 0.5f, 0.5f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, lightAmbient);
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, lightAmbient);
		
		//FloatBuffer materialProp = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.1f, 0.1f, 0.1f, 1f  }).rewind();
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,materialProp);
		
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK,GL11.GL_AMBIENT_AND_DIFFUSE);
		
		
		FloatBuffer lightDiffuse = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.7f, 0.7f, 0.7f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse);
		

		FloatBuffer lightSpec= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.8f, 0.8f, 0.8f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, lightSpec);
		
		FloatBuffer lightPosition = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0f, 0f, -5f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition);
		

		FloatBuffer lightDirection= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0f, 0f, 0f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPOT_DIRECTION, lightDirection);

		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glFrontFace(GL11.GL_CW);
		//GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		GL11.glClearColor(0,0,0,1);
        
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
		
        //GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_DECAL);   // Blending texture and light effect
        GL11.glLightModeli(GL12.GL_LIGHT_MODEL_COLOR_CONTROL, GL12.GL_SEPARATE_SPECULAR_COLOR);
        
		FloatBuffer specref= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.9f, 0.9f, 0.9f, 1f  }).rewind();
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, specref);
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 64);
		
		Assets.spaceinvaders.alien.loadData();
		Assets.models.cube.loadData();
	}
	
	@Override
	public void draw(){
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glLoadIdentity();
		
		// draw quad
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0, 0, 1);
		GL11.glColor4f(1,1,1,1);
		Assets.models.cube.opengldrawtolist();
		Assets.models.cube.opengldraw();
		
//		Assets.spaceinvaders.alien.bind();
		
//		GL11.glBegin(GL11.GL_QUADS);{
//			GL11.glVertex2f(-50,-50);
//            GL11.glTexCoord2f(-1.0f, -1.0f);
//			GL11.glVertex2f(50,-50);
//            GL11.glTexCoord2f(1.0f, -1.0f);
//			GL11.glVertex2f(50,50);
//            GL11.glTexCoord2f(1.0f, 1.0f);
//			GL11.glVertex2f(-50,50);
//            GL11.glTexCoord2f(-1.0f, 1.0f);
//		}
//		GL11.glEnd();
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
