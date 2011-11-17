package grl.prototype.state;

public interface Updateable<T extends State> {
	public void update(T state);
}
