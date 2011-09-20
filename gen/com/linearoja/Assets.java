package com.linearoja;
public class Assets extends com.linearoja.cm.AssetManager{
	public static class models extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.engine.Object3D cube = new com.linearoja.engine.Object3D("res/models/cube.obj");
	}
	public static final com.linearoja.cm.Sound Footsteps = new com.linearoja.cm.Sound("res/Footsteps.wav");
	public static final com.linearoja.cm.Sound ding = new com.linearoja.cm.Sound("res/ding.wav");
	public static final com.linearoja.cm.Sound center = new com.linearoja.cm.Sound("res/center.wav");
	public static class logo extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Asset lwjgl_logowith_jacket = new com.linearoja.cm.Asset("res/logo/lwjgl_logo-with_jacket.svg");
	}
	public static final com.linearoja.cm.Image appletlogo = new com.linearoja.cm.Image("res/appletlogo.png");
	public static final com.linearoja.cm.Image appletprogress = new com.linearoja.cm.Image("res/appletprogress.gif");
	public static final com.linearoja.cm.Sound left = new com.linearoja.cm.Sound("res/left.wav");
	public static class spaceinvaders extends com.linearoja.cm.AssetManager{
		public static final com.linearoja.cm.Image alien3 = new com.linearoja.cm.Image("res/spaceinvaders/alien3.gif");
		public static final com.linearoja.cm.Image pressanykey = new com.linearoja.cm.Image("res/spaceinvaders/pressanykey.gif");
		public static final com.linearoja.cm.Sound start = new com.linearoja.cm.Sound("res/spaceinvaders/start.wav");
		public static final com.linearoja.cm.Sound hit = new com.linearoja.cm.Sound("res/spaceinvaders/hit.wav");
		public static final com.linearoja.cm.Image ship = new com.linearoja.cm.Image("res/spaceinvaders/ship.gif");
		public static final com.linearoja.cm.Image alien2 = new com.linearoja.cm.Image("res/spaceinvaders/alien2.gif");
		public static final com.linearoja.cm.Image youwin = new com.linearoja.cm.Image("res/spaceinvaders/youwin.gif");
		public static final com.linearoja.cm.Image gotyou = new com.linearoja.cm.Image("res/spaceinvaders/gotyou.gif");
		public static final com.linearoja.cm.Image alien = new com.linearoja.cm.Image("res/spaceinvaders/alien.gif");
		public static final com.linearoja.cm.Sound win = new com.linearoja.cm.Sound("res/spaceinvaders/win.wav");
		public static final com.linearoja.cm.Sound loose = new com.linearoja.cm.Sound("res/spaceinvaders/loose.wav");
		public static final com.linearoja.cm.Sound shot = new com.linearoja.cm.Sound("res/spaceinvaders/shot.wav");
	}
	public static final com.linearoja.cm.Sound right = new com.linearoja.cm.Sound("res/right.wav");
}
