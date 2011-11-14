package old.prototype.engine.input.event;

import old.prototype.engine.Broadcast;
import old.prototype.engine.input.ButtonState;
import old.prototype.engine.input.MouseView;

public class MouseEvent extends Broadcast<MouseView>{
	private int x,y;
	private ButtonState leftMouse,rightMouse;
	public MouseEvent(int x, int y, ButtonState leftMouse, ButtonState rightMouse){
		this.x = x;
		this.y = y;
		this.leftMouse = leftMouse;
		this.rightMouse = rightMouse;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public ButtonState getLeftButtonState(){
		return leftMouse;
	}
	public ButtonState getRightButtonState(){
		return rightMouse;
	}
	@Override
	public MouseView getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
