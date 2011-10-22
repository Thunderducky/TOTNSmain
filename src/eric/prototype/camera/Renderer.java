package eric.prototype.camera;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;


public class Renderer {
	private float rotquad = 0.0f;
	public Camera camera;
	public Renderer() throws LWJGLException
	{
		camera = new Camera();
		Display.setDisplayMode(new DisplayMode(800,600));		// Set The Display Mode
        Display.setTitle("Cube Test");								// Add A Title To The Window
        Display.create();										// Create The LWJGL Display	
	}
	
	public void Initialize()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);					
    	GL11.glLoadIdentity();
    	GLU.gluPerspective(45.0f, (float)800/(float)600, 0.1f, 100.0f);
    	
    	GL11.glMatrixMode(GL11.GL_MODELVIEW);					
    	GL11.glLoadIdentity();
    	
    	// Set Options
		GL11.glShadeModel(GL11.GL_SMOOTH);									// Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);							// Black Background
        GL11.glClearDepth(1.0f);											// Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST);									// Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL);									// The Type Of Depth Testing To Do
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);	// Really Nice Perspective Calculations
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);               
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
	}
	
	public void DrawCube(Cube cube)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	// Clear Screen And Depth Buffer
    	GL11.glLoadIdentity();												// Reset The Current Modelview Matrix
    	
		//GL11.glTranslatef(0.0f, 0.0f,-7.0f);					// Translate Into The Screen 7.0 Units
		//GL11.glRotatef(rotquad ,1.0f,1.0f,0.0f);					// Rotate The cube around the Y axis
		SetCameraAngle();
		
		Color.white.bind();
		cube.texture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);							// Draw a cube with quads
		int length = cube.vertices.size();
		for(int i = 0; i < length; i+= 4)	
		{
			for(int j = 0; j < 4; j++)
			{
				Vertex v = cube.vertices.get(i + j);
				GL11.glTexCoord2f(v.textureCoordinate.x,v.textureCoordinate.y);					
				GL11.glVertex3f(v.x + cube.position.x, v.y + cube.position.y, v.z + cube.position.z);					
			}
		}
		GL11.glEnd();

		rotquad +=0.01f;
		Display.update();
	}
	
	public void Shutdown()
	{
		Display.destroy();
	}
	
	private void SetCameraAngle()
	{
		GL11.glRotatef(camera.pitch, 1, 0, 0);
		GL11.glRotatef(camera.yaw, 0, 1, 0);
		GL11.glRotatef(camera.roll, 0, 0, 1);
		GL11.glTranslatef(camera.position.x, camera.position.y, camera.position.z);
	}
}
