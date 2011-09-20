package com.parser.obj;

import java.util.Hashtable;

import com.loader.NormalParser;
import com.loader.LineParserFactory;
import com.loader.WavefrontObject;
import com.parser.mtl.MaterialFileParser;
import com.parser.CommentParser;


public class ObjLineParserFactory extends LineParserFactory{


	
	public ObjLineParserFactory(WavefrontObject object)
	{
		this.object = object;
		parsers.put("v",new VertexParser());
		parsers.put("vn",new NormalParser());
		parsers.put("vp",new FreeFormParser());
		parsers.put("vt",new TextureCooParser());
		parsers.put("f",new FaceParser(object));
		parsers.put("#",new CommentParser());
		parsers.put("mtllib",new MaterialFileParser(object));
		parsers.put("usemtl",new MaterialParser());
	}

	
}
