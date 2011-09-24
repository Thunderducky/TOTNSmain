package com.loader;


import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Go {
	
	static int SCREEN_WIDTH = 1024;
	static int SCREEN_HEIGHT= 768;
	static boolean FULL_SCREEN = false;

private static void createWindow(int screenWidth, int screenHeight, boolean fullscreen) {
	    
		try
		{
		    if (!fullscreen) // create windowed mode
		    	Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
		    else
	    	{
	    		Display.setFullscreen(true);
	            try
	            {
	            	DisplayMode dm[] = org.lwjgl.util.Display.getAvailableDisplayModes(320, 240, -1, -1, -1, -1, 60, 85);
	                org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
	                    "width=" + screenWidth, "height=" + screenHeight, "freq=85", 
	                    "bpp=" + Display.getDisplayMode().getBitsPerPixel()
	                });
	            }
	            catch(Exception e)
	            {
	                Sys.alert("Error", "Could not start full screen, switching to windowed mode");
	                Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
	            }           
	    	}
	
	        Display.create();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
	private static void init()
	{
		createWindow(SCREEN_WIDTH, SCREEN_HEIGHT, FULL_SCREEN) ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		init();
		Timer timer = new Timer();
		timer.update();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glClearColor(1,1,1,1);
		GL11.glClear (GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		Vertex positionFpsText = new Vertex(-SCREEN_WIDTH/2+140,SCREEN_HEIGHT/2-30,0);
		Vertex positionFpsNumber = new Vertex(-SCREEN_WIDTH/2+120,SCREEN_HEIGHT/2-30,0);
		
		
		Font testFont = new Font(positionFpsNumber);
		testFont.setSens(Font.RIGHT_TO_LEFT);
		testFont.setString("");

		Font fps = new Font(positionFpsText);
		fps.setString("fps");
		
		boolean running = true;
		while(running)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			setOrthoMode();
			testFont.draw();
			fps.draw();
			//setProjMode();
			
			//drawSquare();
		
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				running = false;
		
			
			Display.update();
			timer.update();
			testFont.setString(timer.fps);
		}
		Display.destroy();

	}



	private static void setOrthoMode() {
		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
	    GL11.glLoadIdentity(); // Reset The Projection Matrix
	    GLU.gluOrtho2D(-(int)SCREEN_WIDTH/2,(int)SCREEN_WIDTH/2,(int)-SCREEN_HEIGHT/2,(int)SCREEN_HEIGHT/2);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW); 
	}
}
