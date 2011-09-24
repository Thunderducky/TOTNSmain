package com.renderer;

import java.nio.FloatBuffer;

import com.loader.Font;
import com.loader.Timer;
import com.loader.Vertex;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

import com.loader.WavefrontObject;

public class Starter {

	static public final int SCREEN_WIDTH = 1024;
	static public final int SCREEN_HEIGHT = 768; //480
	private static final boolean FULL_SCREEN = false;
		
	private static void initGL() {
    	
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT1);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        
		
		FloatBuffer lightAmbient = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.5f, 0.5f, 0.5f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, lightAmbient);
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, lightAmbient);
		
		//FloatBuffer materialProp = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.1f, 0.1f, 0.1f, 1f  }).rewind();
		//GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,materialProp);
		
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK,GL11.GL_AMBIENT_AND_DIFFUSE);
		
		
		FloatBuffer lightDiffuse = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.7f, 0.7f, 0.7f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse);
		

		FloatBuffer lightSpec= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.8f, 0.8f, 0.8f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, lightSpec);
		
		FloatBuffer lightPosition = (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0f, 0f, -5f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition);
		

		FloatBuffer lightDirection= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0f, 0f, 0f, 1f  }).rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPOT_DIRECTION, lightDirection);

		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glFrontFace(GL11.GL_CW);
		//GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		
		GL11.glClearColor(0,0,0,1);
        
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
		
        //GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_DECAL);   // Blending texture and light effect
        GL11.glLightModeli(GL12.GL_LIGHT_MODEL_COLOR_CONTROL, GL12.GL_SEPARATE_SPECULAR_COLOR);
        
		FloatBuffer specref= (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] { 0.9f, 0.9f, 0.9f, 1f  }).rewind();
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, specref);
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 64);
		
    }
	
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
	
	static double viewAngleX = 0;
	static double viewAngleY = 240;
	static double viewRadius = 0;
	
	public static WavefrontObject obj = null;
	
	public static void main(String[] argv)
	{
		createWindow(SCREEN_WIDTH, SCREEN_HEIGHT, FULL_SCREEN) ;
    	initGL();
    	
    	// The time and accesory: a crappy comment
    	Timer timer = new Timer();
		timer.update();
		
		
		Vertex positionFpsNumber = new Vertex(-SCREEN_WIDTH/2+100,SCREEN_HEIGHT/2-30,0);
		Font testFont = new Font(positionFpsNumber);
		testFont.setSens(Font.RIGHT_TO_LEFT);
		testFont.setString("");

		Vertex positionFpsText = new Vertex(-SCREEN_WIDTH/2+140,SCREEN_HEIGHT/2-30,0);
		Font fps = new Font(positionFpsText);
		fps.setString("fps");
		
		Vertex howToPosition = new Vertex(-SCREEN_WIDTH/2+40,-SCREEN_HEIGHT/2+30,0);
		FadedFont howTo = new FadedFont(howToPosition);
		howTo.setString("To load a model, press 'o'.");
		
		// obj = new WavefrontObject("/Data/DEMO1/DEMO1.OBJ");
		// obj = new WavefrontObject("/Data/IKAL/IKAL.OBJ");
		// obj = new WavefrontObject("/Data/RGA/RGA.OBJ");
		// obj = new WavefrontObject("/Data/EVIL/EVIL.OBJ");
		// obj = new WavefrontObject("/Data/TENSO/TENSO.OBJ");
		 obj = new WavefrontObject("/Data/IKARUGA/IKARUGA2.OBJ");
		// obj = new WavefrontObject("/Data/SKY1/SKY1.OBJ");
		// obj = new WavefrontObject("/Data/DORO01/DORO01.OBJ");
		// obj = new WavefrontObject("/Data/BOY/BOY.OBJ");
		
		viewRadius = - obj.radius *3f ;
		
		MouseHandler mouseHandler = new MouseHandler();
		KeyBoardHandler keyBoardHandler = new KeyBoardHandler();
		
		Kanji kanji = new Kanji();
		
    	boolean running = true;
		while(running)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		    GL11.glLoadIdentity(); // Reset The Projection Matrix
		    GLU.gluOrtho2D(-(int)SCREEN_WIDTH/2,(int)SCREEN_WIDTH/2,(int)-SCREEN_HEIGHT/2,(int)SCREEN_HEIGHT/2);
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		    
		    kanji.render();
			
			
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		    GL11.glLoadIdentity(); // Reset The Projection Matrix
			GLU.gluPerspective(45.0f,SCREEN_WIDTH/SCREEN_HEIGHT,0.1f,10000.0f); 
			GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
			GL11.glMatrixMode(GL11.GL_MODELVIEW); 
			GL11.glLoadIdentity();
			 
			GL11.glPushMatrix();
				GL11.glTranslatef(0,(float)0,(float)viewRadius);
				GL11.glRotatef((float)viewAngleY,0,1,0);
				GL11.glRotatef((float)viewAngleX,1,0,0);
				
				
				//Thread.dumpStack();
				obj.render();
				
			GL11.glPopMatrix();
		
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		    GL11.glLoadIdentity(); // Reset The Projection Matrix
		    GLU.gluOrtho2D(-(int)SCREEN_WIDTH/2,(int)SCREEN_WIDTH/2,(int)-SCREEN_HEIGHT/2,(int)SCREEN_HEIGHT/2);
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		   // GL11.glLoadIdentity();
			testFont.draw();
			fps.draw();
			howTo.draw(timer.delta);
			
			Display.update();
			timer.update();
			mouseHandler.update();
			keyBoardHandler.update();
			
			//angle += rotationSpeed * timer.delta;
			testFont.setString(timer.fps);
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested())
				running = false;
		}
		
		Display.destroy();
	}

}
