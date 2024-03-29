package com.parser.obj;

import com.loader.Material;
import com.loader.WavefrontObject;
import com.parser.LineParser;

public class MaterialParser extends LineParser 
{
		String materialName = "";

		@Override
		public void parse() {
			materialName = words[1];
		}

		@Override
		public void incoporateResults(WavefrontObject wavefrontObject) {
			Material newMaterial = wavefrontObject.getMaterials().get(materialName);
			wavefrontObject.setCurrentMaterial(newMaterial);
			
		}

	

}
