package com.linearoja.cm;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;

@Root
public class Manifest {
	@ElementList(name="AssetTypes")
	List<AssetType> types;

	private static Manifest manifest = null;
	public static Manifest getManifest(){
		if(manifest==null){
			Persister persister = new Persister();
			try {
				manifest = persister.read(Manifest.class, new File("Manifest.xml"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return manifest;
	}

	public Map<String,Class> getAssetHandlers(){
			Map<String,Class> handlers = new HashMap<String,Class>();
			for(AssetType value:types){
				try {
					handlers.put(value.extension, Class.forName(value.handler));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					handlers.put(value.extension, Asset.class);
				}
			}
			return handlers;
	}
}
@Root
class AssetType{
	@Attribute 
	String extension;
	@Attribute
	String handler;
}
