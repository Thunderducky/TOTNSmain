package com.linearoja.fractals.transforms;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public abstract class Transform implements Cloneable{
	private FloatBuffer matrix = BufferUtils.createFloatBuffer(16);
	public void apply(){
		GL11.glLoadMatrix(matrix);
		onApply();
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, matrix);
	}
	public Transform compose(Transform transform){
		GL11.glPushMatrix();
		this.apply();
		transform.apply();
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, matrix);
		GL11.glPopMatrix();
		return this;
	}
	public void setMatrix(FloatBuffer buffer){
		this.matrix = buffer;
	}
	public FloatBuffer getMatrix(){
		return matrix;
	}
	
	public Transform scale(float scale){
		return compose(new Scale(scale));
	}
	public Transform translate(float x, float y){
		return compose(new Translate(x,y));
	}
	public Transform rotate(float angle){
		return compose(new Rotate(angle));
	}
	public abstract void onApply();
	
	public Transform clone(){
		return new MatrixTransform(getMatrix());
	}
}
