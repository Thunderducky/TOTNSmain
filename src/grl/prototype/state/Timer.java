package grl.prototype.state;

public class Timer {
	private long lastReset;
	public long difference(State state){
		return state.getTime()-lastReset;
	}
	public void reset(State state){
		lastReset = state.getTime();
	}
	
}
