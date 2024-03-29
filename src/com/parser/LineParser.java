package com.parser;

import com.loader.WavefrontObject;

public abstract class LineParser 
{

	protected String[] words = null ;
	
	public void setWords(String[] words)
	{
		this.words = words;
	}
	public abstract void parse();
	
	public abstract void incoporateResults(WavefrontObject wavefrontObject);
		
}
