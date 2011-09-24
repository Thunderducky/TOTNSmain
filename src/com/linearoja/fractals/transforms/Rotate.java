package com.linearoja.fractals.transforms;

import org.lwjgl.opengl.GL11;

public class Rotate extends Transform{
	private float angle;
	public Rotate(float angle){
		this.angle = angle;
	}
	@Override
	public void onApply() {
		GL11.glRotatef(angle, 0, 0, 1);
	}

}
