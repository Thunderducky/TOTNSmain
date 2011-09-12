package com.linearoja.fractals.transforms;

import org.lwjgl.opengl.GL11;

public class Translate extends Transform{
	float x , y;
	public Translate(float x, float y){
		this.x = x;
		this.y = y;
	}
	@Override
	public void onApply() {
		GL11.glTranslatef(x, y, 0);
	}

}
