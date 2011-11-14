package grl.prototype.animation.interpolate;

public interface InterpolationFunction {
	public double getInterpolationValue(long start, long current);
	public boolean isComplete(long start,long current);
}
