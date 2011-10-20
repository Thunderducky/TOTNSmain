package eric.prototype.camera;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;		// Import LWJGL Display
import org.lwjgl.opengl.DisplayMode;	// Import LWJGL DisplayMode
import org.lwjgl.opengl.GL11;			// Import LWJGL GL11
import org.lwjgl.input.Keyboard;		// Import LWJGL Input
import javax.swing.JOptionPane; 		// Import Swing JOptionPane
import org.lwjgl.util.glu.GLU;


public class Main 						// The Main Class
{
    public static void main(String args[])						// The Static Main Function
    {
    	try {
    		BasicGame game = new BasicGame();
    		game.Initialize();
        	while(game.Update());
        	game.Shutdown();
    	} catch(LWJGLException ex)
    	{
    		System.exit(0);
    	}
    	
    	
    }	

} // End Main Class

