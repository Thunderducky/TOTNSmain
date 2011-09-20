package com.parser.mtl;

import com.loader.Texture;
import com.loader.TextureLoader;

import com.loader.Material;
import com.loader.WavefrontObject;
import com.parser.LineParser;

public class KdMapParser extends LineParser {

	private Texture texture = null;
	private WavefrontObject object = null;
	
	public KdMapParser(WavefrontObject object)
	{
		this.object = object;
	}
	
	@Override
	public void incoporateResults(WavefrontObject wavefrontObject) {
		
		if (texture != null)
		{
			Material currentMaterial = wavefrontObject.getCurrentMaterial() ;
			currentMaterial.setTexture(texture);
		}
	}

	@Override
	public void parse() {
		String textureFileName = words[words.length-1];	
		String pathToTextureBinary = object.getContextfolder() +  textureFileName;
		texture = TextureLoader.instance().loadTexture(pathToTextureBinary);
	}

}
