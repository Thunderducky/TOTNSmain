package com.linearoja.cm;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Font  extends Asset implements Loadable{
	private static String[] extensions = new String[]{"ttf"};
	private static Map<Integer,TrueTypeFont> fonts = new HashMap<Integer,TrueTypeFont>();
	private String path;
	private static java.awt.Font awtFont;
	public Font(String path) {
		super(path);
		this.path = path;
	}

	@Override
	public void loadData() {
			
		// load font from a .ttf file
		try {
			if(awtFont==null){
				InputStream inputStream	= ResourceLoader.getResourceAsStream(path);
				awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getAcceptedExtensions() {
		return extensions;
	}
	
	public TrueTypeFont getFont(int size){
		TrueTypeFont font;
		if(!fonts.containsKey(size)){
			java.awt.Font tempFont = awtFont.deriveFont((float)size); // set font size
			font = new TrueTypeFont(tempFont, true);
			fonts.put(size, font);
		}
		else{
			font = fonts.get(size);
		}
		return font;
	}

}
