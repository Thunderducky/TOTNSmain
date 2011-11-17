package grl.prototype.example.state.pong;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;

import java.io.Serializable;

public class Ball implements Serializable,Modifiable<GameState>{
	private float x,y;
	private final float radius = 10;
	
	private transient boolean modified = false;
	
	public Ball(){
		this.x = .5f;
		this.y = .5f;
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
	@Override
	public void update(GameState state) {
		modified = false;
	}
	@Override
	public boolean isModified() {
		return modified;
	}
}
