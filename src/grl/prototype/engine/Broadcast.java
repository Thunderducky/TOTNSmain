package grl.prototype.engine;

import grl.prototype.engine.input.MouseView;

public abstract class Broadcast<T extends View> {

	public abstract T getView();
}
