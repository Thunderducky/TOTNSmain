package eric.prototype;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class KeyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputDevicesSystem iSystem = new InputDevicesSystem();
		
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		iSystem.Initialize();
		
		KeyboardView keyView = iSystem.Access().GetKeyboardView();
		while(!keyView.IsKeyPressed(Keyboard.KEY_ESCAPE))
		{
			iSystem.Update();
			if(keyView.IsNewKeyPress(Keyboard.KEY_A))
				System.out.println("NEFARIOUS!");
			if(keyView.IsNewKeyRelease(Keyboard.KEY_B))
				System.out.println("SASQUATCH?");
			Display.update();
			
		}
		
	}

}
