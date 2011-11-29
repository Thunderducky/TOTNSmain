package grl.prototype.example.client.graphics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

import com.linearoja.Assets;

import grl.prototype.example.state.GameState;
import grl.prototype.example.state.pong.Ball;
import grl.prototype.example.state.pong.Paddle;
import grl.prototype.example.state.pong.Player;
import grl.prototype.example.state.pong.PongState;
import grl.prototype.graphics.Renderer;
public class PongRenderer implements Renderer<GameState>{
	private boolean enabled = true;
	private TrueTypeFont font;
	@Override
	public void init() {
		Assets.fonts.BleedingCowboys.loadData();
		font = Assets.fonts.BleedingCowboys.getFont(16);
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


		Player player1 = state.getPlayer(Player.Number.One);
		Player player2 = state.getPlayer(Player.Number.Two);
		Paddle paddle1 = player1.getPaddle();
		Paddle paddle2 = player2.getPaddle();
		Ball ball = state.getBall();


		int yOffset = (int)(paddle1.getY()*height);
		int paddleHeight = (int)(paddle1.height*height);
		int paddleWidth = (int)(paddle1.width*width);



		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);{
			font.drawString(0, height-16, player1.getUsername());
			font.drawString(width-20, height-16, player2.getUsername());
		}GL11.glDisable(GL11.GL_BLEND);

		GL11.glLoadIdentity();
		GL11.glPushMatrix();
		{
			GL11.glColor4f(0.5f,0.5f,1.0f,1f);
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

			//draw ball
			GL11.glPushMatrix();{
				GL11.glTranslatef(ball.getX()*width, ball.getY()*height, 0);
				GL11.glBegin(GL11.GL_QUADS);{

					GL11.glVertex2i(-5,-5);
					GL11.glVertex2i(5,-5);
					GL11.glVertex2i(5, 5);
					GL11.glVertex2i(-5, 5);
				}
				GL11.glEnd();
			}
			GL11.glPopMatrix();
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
