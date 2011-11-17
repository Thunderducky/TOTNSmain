package grl.prototype.graphics;

import grl.prototype.state.State;

public interface Drawable<T extends State> {
	public void draw(T state);
}
