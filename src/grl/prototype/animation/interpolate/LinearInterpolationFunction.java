package grl.prototype.animation.interpolate;

public class LinearInterpolationFunction implements InterpolationFunction{
	private double length;
	public LinearInterpolationFunction(long length){
		this.length = length;
	}

	@Override
	public double getInterpolationValue(long start, long current) {
		return (current-start)/length;
	}

	@Override
	public boolean isComplete(long start,long current) {
		return (start + length)<current;
	}
}
