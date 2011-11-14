package com.linearoja.cm;

import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Font  extends Asset implements Loadable{
	private static String[] extensions = new String[]{"ttf"};
	private String path;
	private TrueTypeFont font;
	public Font(String path) {
		super(path);
		this.path = path;
	}

	@Override
	public void loadData() {
			
		// load font from a .ttf file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(path);
			
			java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f); // set font size
			font = new TrueTypeFont(awtFont2, true);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getAcceptedExtensions() {
		return extensions;
	}
	
	public TrueTypeFont getFont(){
		return font;
	}

}
