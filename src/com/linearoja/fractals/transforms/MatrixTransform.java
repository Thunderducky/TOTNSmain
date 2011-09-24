package com.linearoja.fractals.transforms;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class MatrixTransform extends Transform{
	public MatrixTransform(){
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, getMatrix());
	}
	public MatrixTransform(FloatBuffer buffer){
		setMatrix(buffer.duplicate());
	}

	@Override
	public void onApply() {
	}
}
