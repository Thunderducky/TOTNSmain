package com.linearoja.fractals;

import org.lwjgl.opengl.GL11;

public class Staircase extends Fractal{

	@Override
	public void draw(int depth) {
		for(int i=1; i<depth; i++){
			float scale = (float)Math.pow(.5, i);
			GL11.glPushMatrix();
			{
				GL11.glScalef(scale, scale, 1);
				GL11.glPushMatrix();
				GL11.glTranslatef(-90, 90, 0);
				GL11.glColor3f(0.5f,0.5f,1.0f);
				drawSquare();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslatef(90, -90, 0);
				GL11.glColor3f(1.0f,0.5f,0.5f);
				drawSquare();
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			{
				scale = (float)Math.pow(.5, i-1);
				GL11.glScalef(scale, scale, 1);
				drawSquare();
			}
			GL11.glPopMatrix();
		}
	}
	public void drawSquare(){

		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex3f(-90, -90, 0);
			GL11.glVertex3f(0, -90, 0);
			GL11.glVertex3f(0, 0, 0);
			GL11.glVertex3f(-90, 0, 0);
		}
		GL11.glEnd();
	}

}
