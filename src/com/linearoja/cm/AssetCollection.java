package com.linearoja.cm;

import java.util.ArrayList;
import java.util.List;

public class AssetCollection {
	private static List<Asset> assets = new ArrayList<Asset>();
	protected static void appendAsset(Asset asset){
		assets.add(asset);
	}
}
