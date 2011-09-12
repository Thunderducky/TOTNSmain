package com.linearoja.fractals.shapes;

import org.lwjgl.opengl.GL11;

public class Triangle extends Shape{

	@Override
	protected void onDraw() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			GL11.glVertex3f(0, 56, 0);
			GL11.glVertex3f(90, -90, 0);
			GL11.glVertex3f(-90, -90, 0);
		}
		GL11.glEnd();
	}

	public Triangle clone(){
		Triangle t = new Triangle();
		t.setTransform(getTransform().clone());
		return t;
	}
}
