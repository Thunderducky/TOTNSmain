package com.linearoja;
public class Assets extends com.linearoja.cm.AssetManager{
	public static class models extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Asset cube = new com.linearoja.cm.Asset("res/models/cube.obj");
	}
	public static final com.linearoja.cm.Sound Footsteps = new com.linearoja.cm.Sound("res/Footsteps.wav");
	public static final com.linearoja.cm.Sound ding = new com.linearoja.cm.Sound("res/ding.wav");
	public static final com.linearoja.cm.Sound center = new com.linearoja.cm.Sound("res/center.wav");
	public static class scripts extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Script server = new com.linearoja.cm.Script("res/scripts/server.py");
	}
	public static class logo extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Asset lwjgl_logowith_jacket = new com.linearoja.cm.Asset("res/logo/lwjgl_logo-with_jacket.svg");
	}
	public static class camerademoassets extends com.linearoja.cm.AssetManager{
		public static class images extends com.linearoja.cm.AssetManager{
			public static final com.linearoja.cm.Image defaultcubeTexture = new com.linearoja.cm.Image("res/CameraDemoAssets/images/defaultcubeTexture.png");
			public static final com.linearoja.cm.Image Template = new com.linearoja.cm.Image("res/CameraDemoAssets/images/Template.png");
			public static final com.linearoja.cm.Image test = new com.linearoja.cm.Image("res/CameraDemoAssets/images/test.png");
			public static final com.linearoja.cm.Image testOld = new com.linearoja.cm.Image("res/CameraDemoAssets/images/testOld.png");
			public static final com.linearoja.cm.Image Grass = new com.linearoja.cm.Image("res/CameraDemoAssets/images/Grass.png");
			public static final com.linearoja.cm.Image format = new com.linearoja.cm.Image("res/CameraDemoAssets/images/format.png");
			public static final com.linearoja.cm.Image DirtTrial = new com.linearoja.cm.Image("res/CameraDemoAssets/images/DirtTrial.png");
			public static final com.linearoja.cm.Image TestIndividuals = new com.linearoja.cm.Image("res/CameraDemoAssets/images/TestIndividuals.png");
		}
	}
	public static final com.linearoja.cm.Image appletlogo = new com.linearoja.cm.Image("res/appletlogo.png");
	public static final com.linearoja.cm.Image appletprogress = new com.linearoja.cm.Image("res/appletprogress.gif");
	public static final com.linearoja.cm.Sound left = new com.linearoja.cm.Sound("res/left.wav");
	public static class fonts extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Font BleedingCowboys = new com.linearoja.cm.Font("res/fonts/Bleeding Cowboys.ttf");
		public static final com.linearoja.cm.Font FOO = new com.linearoja.cm.Font("res/fonts/FOO.ttf");
	}
	public static final com.linearoja.cm.Sound right = new com.linearoja.cm.Sound("res/right.wav");
}
