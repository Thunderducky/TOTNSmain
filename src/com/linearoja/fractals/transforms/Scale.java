package com.linearoja.fractals.transforms;

import org.lwjgl.opengl.GL11;

public class Scale extends Transform{
	private float scale;
	public Scale(float scale){
		this.scale = scale;
	}
	public void onApply(){
		GL11.glScalef(scale, scale, 1f);
	}
}
