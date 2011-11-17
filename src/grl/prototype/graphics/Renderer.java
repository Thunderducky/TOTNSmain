package grl.prototype.graphics;

import grl.prototype.example.state.GameState;
import grl.prototype.state.State;
import grl.prototype.state.Updateable;

public interface Renderer<T extends State> extends Updateable<T>,Drawable<T>{
	public void init();
	public void update(T state);
	public void draw(T state);
	public boolean isEnabled();
	public void setEnabled(boolean enabled);
}
