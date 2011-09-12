package com.linearoja.fractals;

import org.lwjgl.opengl.GL11;

public class Sierpinsky extends Fractal{

	@Override
	public void draw(int depth) {
		if(depth>0){
			GL11.glScalef(.5f, .5f, 1);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 56, 0);
			GL11.glColor3f(0.5f,0.5f,1.0f);
			draw(depth-1);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef(90, -90, 0);
			GL11.glColor3f(1.0f,0.5f,0.5f);
			draw(depth-1);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef(-90, -90, 0);
			GL11.glColor3f(0.5f,1f,0.5f);
			draw(depth-1);
			GL11.glPopMatrix();
		}
		if(depth==0){
			drawTriangle();
		}
	}
	private void drawTriangle(){
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex3f(0, 56, 0);
			GL11.glVertex3f(90, -90, 0);
			GL11.glVertex3f(-90, -90, 0);
		}
		GL11.glEnd();
	}

}
