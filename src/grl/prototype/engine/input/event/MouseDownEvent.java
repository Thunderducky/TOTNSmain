package grl.prototype.engine.input.event;

import grl.prototype.engine.Broadcast;
import grl.prototype.engine.input.ButtonState;
import grl.prototype.engine.input.MouseView;

public class MouseDownEvent extends MouseEvent{

	public MouseDownEvent(int x, int y, ButtonState leftMouse,
			ButtonState rightMouse) {
		super(x, y, leftMouse, rightMouse);
	}


}
