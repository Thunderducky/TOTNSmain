package com.linearoja.fractals.shapes;

import org.lwjgl.opengl.GL11;

import com.linearoja.fractals.transforms.MatrixTransform;
import com.linearoja.fractals.transforms.Rotate;
import com.linearoja.fractals.transforms.Scale;
import com.linearoja.fractals.transforms.Transform;
import com.linearoja.fractals.transforms.Translate;

public abstract class Shape implements Cloneable{
	private Transform transform = new MatrixTransform();
	
	
	public void setTransform(Transform t){
		this.transform = t;
	}
	public Transform getTransform(){
		return this.transform;
	}
	public void draw(){
		GL11.glPushMatrix();
		transform.apply();
		onDraw();
		GL11.glPopMatrix();
	}
	protected abstract void onDraw();

	public Transform scale(float scale){
		Scale transformObj = new Scale(scale);
		if(transform == null)
			transform = transformObj;
		else
			transform.compose(transformObj);
		return transformObj;
	}
	public Transform translate(float x, float y){
		Transform transformObj = new Translate(x,y);
		if(transform == null)
			transform = transformObj;
		else
			transform.compose(transformObj);
		return transformObj;
	}
	public Transform rotate(float angle){
		Transform transformObj = new Rotate(angle);
		if(transform == null)
			transform = transformObj;
		else
			transform.compose(transformObj);
		return transformObj;
	}
	public Shape clone(){
		return null;
	}
}
