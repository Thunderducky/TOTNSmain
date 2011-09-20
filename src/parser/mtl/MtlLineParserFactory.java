package com.parser.mtl;

import java.util.Hashtable;

import com.loader.NormalParser;
import com.loader.LineParserFactory;
import com.loader.WavefrontObject;
import com.parser.mtl.MaterialFileParser;
import com.parser.mtl.KdMapParser;
import com.parser.mtl.KdParser;
import com.parser.mtl.MaterialParser;
import com.parser.CommentParser;
import com.parser.DefaultParser;
import com.parser.LineParser;

public class MtlLineParserFactory extends LineParserFactory{

	
	
	public MtlLineParserFactory(WavefrontObject object)
	{
		this.object = object;
		parsers.put("newmtl",new MaterialParser());
		parsers.put("Kd",new KdParser());
		parsers.put("map_Kd",new KdMapParser(object));
		parsers.put("#",new CommentParser());
	}
	
	
		
	
		
	
}
