package grl.prototype.animation;

import grl.prototype.animation.interpolate.InterpolationFunction;
import grl.prototype.animation.interpolate.Interpolator;
import grl.prototype.example.state.GameState;

public class Animation {
	private long startTime;
	private Interpolator interpolator;
	private InterpolationFunction function;
	private boolean complete;
	public Animation(Interpolator modifier, InterpolationFunction interpolator){
		this.interpolator = modifier;
		this.function = interpolator;
	}
	public void start(GameState state){
		startTime = state.getTime();
		complete = false;
		interpolator.onStart();
	}
	public void update(GameState state){
		long currentTime = state.getTime();
		if(!function.isComplete(startTime,currentTime)){
			double interpolationValue = function.getInterpolationValue(startTime, currentTime);
			interpolator.interpolate(interpolationValue);
		}
		else{
			interpolator.onComplete();
			complete = true;
		}
	}
	public boolean isComplete(){
		return complete;
	}
}
