package old.prototype.engine.input.event;

import old.prototype.engine.Broadcast;
import old.prototype.engine.input.ButtonState;
import old.prototype.engine.input.MouseView;

public class MouseDownEvent extends MouseEvent{

	public MouseDownEvent(int x, int y, ButtonState leftMouse,
			ButtonState rightMouse) {
		super(x, y, leftMouse, rightMouse);
	}


}
