package com.linearoja.cm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class Script extends Asset implements Loadable{
	private static String[] extensions = new String[]{"py"};
	private String contents;
	public Script(String path) {
		super(path);
	}

	@Override
	public void loadData() {
		contents = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(this.getFile()));
			String line;
			while((line = reader.readLine())!=null)
				contents += line + "\n";
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getContents(){
		return contents;
	}
	
	@Override
	public String[] getAcceptedExtensions() {
		return extensions;
	}

}
