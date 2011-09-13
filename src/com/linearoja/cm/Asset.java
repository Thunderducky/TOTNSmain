package com.linearoja.cm;

import java.io.File;

public class Asset {
	private static String UNALLOWED_CHARACTERS = "[^(\\w|_)]";
	
	
	private File file; 
	private String relativePath,name,extension;
	
	
	public Asset(String path){ 
		file = new File(path); 
	}
	
	public File getFile(){
		return file; 
	}
	public String getName(){
		return name;
	} 
	public String getExtension(){
		return extension;
	}
	
	private void setRelativePath(File file, File rootDir){
		String absPath = file.getPath();
		String rootPath = rootDir.getPath()+"/";
		if(absPath.startsWith(rootPath)){
			relativePath = absPath.replace(rootPath, "");
		}
	}
	private void sanitizeName(File file){
		String dirtyName = file.getName();
		String[] parts = dirtyName.split("\\.");
		extension = parts[parts.length-1].toLowerCase();
		name = sanitizeString(dirtyName.substring(0,dirtyName.length()-extension.length()));
	}
	public static String sanitizeString(String dirty){
		return dirty.replaceAll(UNALLOWED_CHARACTERS, "");
	}
	public String toString(){
		return relativePath + " " + name +"."+extension;
	}
	protected static Asset makeAsset(File file, File rootDir){
		Asset asset = new Asset(file.getPath());
		asset.sanitizeName(file);
		asset.setRelativePath(file,rootDir);
		return asset;
	}
}
