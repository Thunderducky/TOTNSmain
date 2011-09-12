package com.linearoja.fractals.shapes;

import org.lwjgl.opengl.GL11;

public class Square extends Shape{

	@Override
	protected void onDraw() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex3f(-90, -90, 0);
			GL11.glVertex3f(0, -90, 0);
			GL11.glVertex3f(0, 0, 0);
			GL11.glVertex3f(-90, 0, 0);
		}
		GL11.glEnd();
	}
	
	public Square clone(){
		Square s = new Square();
		s.setTransform(this.getTransform().clone());
		return s;
	}
}
