package grl.prototype.animation.interpolate;

public interface Interpolator {
	public void interpolate(double t);
	public void onStart();
	public void onComplete();
}
