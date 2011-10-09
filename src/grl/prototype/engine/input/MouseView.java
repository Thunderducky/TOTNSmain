package grl.prototype.engine.input;

import grl.prototype.engine.View;

public class MouseView extends View{
	private MouseState previousMouseState,newMouseState;
	public MouseView(MouseState previousMouseState, MouseState newMouseState){
		this.previousMouseState = previousMouseState;
		this.newMouseState = newMouseState;
	}
	public int getDeltaX(){
		return newMouseState.getX() - previousMouseState.getX();
	}
	public int getDeltaY(){
		return newMouseState.getY() - previousMouseState.getY();
	}
	public int getX(){
		return newMouseState.getX();
	}
	public int getY(){
		return newMouseState.getY();
	}
}
