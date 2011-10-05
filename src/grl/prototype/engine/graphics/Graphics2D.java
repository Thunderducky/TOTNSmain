package grl.prototype.engine.graphics;

import org.lwjgl.opengl.GL11;

import grl.prototype.engine.Controller;

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
