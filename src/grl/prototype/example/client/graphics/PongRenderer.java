package grl.prototype.example.client.graphics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import grl.prototype.example.state.GameState;
import grl.prototype.example.state.pong.Paddle;
import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;
import grl.prototype.graphics.Renderer;
public class PongRenderer implements Renderer<GameState>{
	private boolean enabled = true;
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameState gameState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GameState gameState) {
		PongState state = gameState.getPongState();
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();

		Paddle paddle1 = state.getPlayer(Player.Number.One).getPaddle();
		Paddle paddle2 = state.getPlayer(Player.Number.Two).getPaddle();


		int yOffset = (int)(paddle1.getY()*height);
		int paddleHeight = (int)(paddle1.height*height);
		int paddleWidth = (int)(paddle1.width*width);



		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glLoadIdentity();
		GL11.glPushMatrix();
		{
			//draw paddle 1
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2i(0,yOffset);
			GL11.glVertex2i(0+paddleWidth, yOffset);
			GL11.glVertex2i(0+paddleWidth, yOffset+paddleHeight);
			GL11.glVertex2i(0, yOffset+paddleHeight);
			GL11.glEnd();


			yOffset = (int)(paddle2.getY()*height);
			//draw paddle 2
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2i(width-paddleWidth,yOffset);
			GL11.glVertex2i(width, yOffset);
			GL11.glVertex2i(width, yOffset+paddleHeight);
			GL11.glVertex2i(width-paddleWidth, yOffset+paddleHeight);
			GL11.glEnd();
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
