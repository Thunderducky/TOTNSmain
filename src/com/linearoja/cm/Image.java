package com.linearoja.cm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Image extends Asset implements Loadable{
	Texture texture;
	
	
	public Image(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadData() {
		try {
			String type = this.getExtension().toUpperCase();
			texture = TextureLoader.getTexture(type, new FileInputStream(this.getFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Texture getTexture(){
		return texture;
	}
	public void bind(){
		texture.bind();
	}
	public int getTextureID(){
		return texture.getTextureID();
	}

	@Override
	public String[] getAcceptedExtensions() {
		return new String[]{"png"};
	}

}
