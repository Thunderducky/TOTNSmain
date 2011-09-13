package com.linearoja.cm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Generator {
	public static void buildAssetClass(File contentRoot,String className,String packageName){
		ClassModel model = ClassModel.buildFromDirectory(className, packageName, contentRoot);
		model.createFile(model.toClassString());
		//System.out.println(model.toClassString());
	}
	public static void main(String[] args){
		buildAssetClass(new File("res"),"Assets","com.linearoja");
	}
}
class ClassModel{ 
	private Map<String,Class> typeHandlers;
	private static int counter = 0; 
	
	String className,packageName;
	Map<String,Object> objects = new HashMap<String,Object>();
	
	private ClassModel(String className, String packageName,Map<String,Class> typeHandlers){
		this.className = className; 
		this.packageName = packageName;
		this.typeHandlers = typeHandlers;
	}
	
	
	public String toClassString(){
		String classString = "package "+packageName+";\n";
		classString += getSubclassString(className,objects,"","public");
		return classString;
	}
	private String getSubclassString(String name,Map<String,Object> subObjects,String linePrefix, String classPrefix){
		String subclassString = linePrefix+classPrefix+" class "+name+" extends "+AssetManager.class.getCanonicalName()+"{\n";
		for(String key:subObjects.keySet()){
			Object object = subObjects.get(key);
			if(object instanceof Asset){
				Asset meta = (Asset)object;
				String className = meta.getClass().getCanonicalName();
				if(typeHandlers.containsKey(meta.getExtension()))
					className = typeHandlers.get(meta.getExtension()).getCanonicalName();
				String path = meta.getFile().getPath().replace('\\', '/');
				subclassString += linePrefix+"\tpublic static final "+className+" "+
				meta.getName()+" = new "+className+"(\""+path+"\");\n";
			}
			else if(object instanceof Map){
				subclassString += getSubclassString(key,(Map<String,Object>)object,"\t"+linePrefix, "public static");
			}
		}
		subclassString += linePrefix+"}\n";
		return subclassString;
	}
	protected void createFile(String content){
		String path = packageName+"."+className;
		path = path.replace(".", "/");
		File file = new File("gen/"+path+".java");
		try {
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			writer.append(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static ClassModel buildFromDirectory(String className, String packageName, File directory){
		
		
		ClassModel model = new ClassModel(className,packageName,Manifest.getManifest().getAssetHandlers());
		counter = 0;
		model.objects = getFromDirectory(directory);
		
		return model;
	}
	
	private static Map<String,Object> getFromDirectory(File directory){
		HashMap<String,Object> map = new HashMap<String,Object>();
		for(File file: directory.listFiles()){
			if(file.isFile() && !file.isHidden()){
				Asset meta = Asset.makeAsset(file,directory);
				counter ++;
				map.put(meta.getName(), meta);
			}  
			else if(file.isDirectory()){
				if(file.listFiles().length>0)
				map.put(Asset.sanitizeString(file.getName()).toLowerCase(), getFromDirectory(file));
			}
		}
		return map;
	}
}
