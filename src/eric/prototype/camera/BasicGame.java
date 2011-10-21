package eric.prototype.camera;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;		// Import LWJGL Display
import org.lwjgl.opengl.DisplayMode;	// Import LWJGL DisplayMode
import org.lwjgl.opengl.GL11;			// Import LWJGL GL11
import org.lwjgl.input.Keyboard;		// Import LWJGL Input
import org.lwjgl.input.Mouse;

import javax.swing.JOptionPane; 		// Import Swing JOptionPane
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.linearoja.Assets;
import com.linearoja.cm.Image;

public class BasicGame {
	private boolean done = false;
	private float rotquad = 0.0f;
	private Cube testCube;
	Renderer gameRenderer;
	CameraController cc;
	public BasicGame() throws LWJGLException
	{
		gameRenderer = new Renderer();
	}
	public void Initialize()
	{ 
        // Set the Matrices
        gameRenderer.Initialize();
        testCube = new Cube();
        Mouse.setCursorPosition(400, 300);
        Mouse.setGrabbed(true);
        cc = new CameraController(gameRenderer.camera);
        try
        {
        	Image image = Assets.camerademoassets.images.Grass;
        	image.loadData();
        	testCube.texture = image.getTexture();
        	testCube.ApplyTextureDefault();
        }
        catch(Exception e)
        {
        	
        }
	}
	public boolean Update()
	{
		int dx = Mouse.getDX();
		int dy = Mouse.getDY();
		
		cc.camera.yaw += dx * 0.05f;
		cc.camera.pitch -= dy * 0.05f;
		float speed = 0.0005f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
			cc.walkForward(speed, cc.camera.yaw);
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			cc.walkBackwards(speed, cc.camera.yaw);
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			cc.strafeLeft(speed, cc.camera.yaw);
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			cc.strafeRight(speed, cc.camera.yaw);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			cc.camera.position.y -= speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			cc.camera.position.y += speed;
			
		gameRenderer.DrawCube(testCube);
		done = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested();
		return !done;
		
	}
	public void Shutdown()
	{
		Display.destroy();
	}
	
}
