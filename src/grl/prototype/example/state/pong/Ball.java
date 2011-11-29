package grl.prototype.example.state.pong;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;

import java.io.Serializable;

public class Ball implements Serializable,Modifiable<GameState>{
	private float x,y;
	private final float radius = 10;
	private float dx,dy;
	
	private boolean modified = false;
	
	public Ball(){
		this.x = .5f;
		this.y = .5f;
	}
	public Ball(Ball ball){
		copyValues(ball);
	}
	public void copyValues(Ball ball){
		this.x = ball.x;
		this.dx = ball.dx;
		this.y = ball.y;
		this.dy = ball.dy;
		this.modified = ball.modified;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getRadius(){
		return radius;
	}
	public void setX(float x){
		if(x!=this.x){
			this.x = x;
			modified = true;
		}
	}
	public void setY(float y){
		if(y!=this.y){
			this.y = y;
			modified = true;
		}
	}
	public void setDX(float dx){
		if(dx!=this.dx){
			this.dx = dx;
			modified = true;
		}
	}
	public void setDY(float dy){
		if(dy!=this.dy){
			this.dy = dy;
			modified = true;
		}
	}
	@Override
	public void update(GameState state) {
		if(this.x<=0){
			setDX(.25f);
		}
		else if(this.x >=1){
			setDX(-.25f);
		}
		if(this.y<=0){
			setDY(.25f);
		}
		else if(this.y>=1){
			setDY(-.25f);
		}
		setX(x+dx*state.getTimeDeltaSeconds());
		setY(y+dy*state.getTimeDeltaSeconds());
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
