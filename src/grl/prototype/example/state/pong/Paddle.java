package grl.prototype.example.state.pong;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;

import java.io.Serializable;

public class Paddle implements Serializable,Modifiable<GameState>{
	public static final float MOVE_RATE = 1.5f;
	private float y;
	private float rate = 0;
	public final float height;
	public final float width;

	private boolean modified = false;

	public Paddle(){
		y = 0;
		height = .1f;
		width = .05f;
	}
	public void setY(float y){
		if(y<=0){
			this.y = 0;
			modified = true;

		}else if(y>=1-height){
			this.y = 1-height;
			modified = true;

		}else{
			this.y = y;
			modified = true;


		}
	}
	public void setRate(float rate){
		if(rate!=this.rate){
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
	@Override
	public void update(GameState state) {
		this.setY(this.getY()+this.getRate()*state.getTimeDeltaSeconds());
		modified = false;
	}
	@Override
	public boolean isModified() {
		return modified;
	}
}
