package grl.prototype.example.state.pong;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;

import java.io.Serializable;

public class Paddle implements Serializable,Modifiable<GameState>{
	public static final float MOVE_RATE = 1.5f;
	private float y;
	private float rate = 0;
	public final float height = .1f;
	public final float width = .05f;

	private boolean modified = false;

	public Paddle(){
		y = 0;
	}
	public Paddle(Paddle paddle){
		this.copyValues(paddle);
	}
	public void setY(float y){
		if(y<=0){
			y = 0;

		}else if(y>=1-height){
			y = 1-height;

		}
		if(Math.abs(y - this.y)>.001){
			this.y = y;
			modified = true;
		}
	}
	public void setRate(float rate){
		if(Math.abs(rate - this.rate)>.001){
			this.rate = rate;
			this.modified = true;
		}
	}
	public float getY(){
		return y;
	}
	public float getRate(){
		return rate;
	}
	public void copyValues(Paddle paddle){
		this.setY(paddle.getY());
		this.setRate(paddle.getRate());
		modified = paddle.modified;
	}
	@Override
	public void update(GameState state) {
		this.setY(this.getY()+this.getRate()*state.getTimeDeltaSeconds());
	}
	@Override
	public boolean isModified() {
		return modified;
	}
	@Override
	public void clearModified() {
		modified = false;
	}
}
