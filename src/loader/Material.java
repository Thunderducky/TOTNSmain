package com.loader;

import loader.Texture;
import loader.Vertex;

public class Material {

	private Texture texture;
	private Vertex Kd;
	private String name;
	
	public Material(String name)
	{
		this.name = name;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vertex getKd() {
		return Kd;
	}

	public void setKa(Vertex kd) {
		Kd = kd;
	}
	
	
}
