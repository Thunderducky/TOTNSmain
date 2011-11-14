package old.prototype.engine.graphics;

import old.prototype.engine.Controller;

import org.lwjgl.opengl.GL11;


public class Graphics2D implements Controller{

	@Override
	public void update(long timeDelta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
	}

	@Override
	public void fireEvents() {
		// TODO Auto-generated method stub
		
	}

}
