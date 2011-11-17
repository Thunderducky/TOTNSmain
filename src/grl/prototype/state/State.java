package grl.prototype.state;

public abstract class State {
	private long currentTime;
	private long deltaTime = 0;
	private float deltaTimeSeconds = 0;
	public final void updateTime(){
		long newTime = System.currentTimeMillis();
		deltaTime = newTime - currentTime;
		deltaTimeSeconds = ((float)deltaTime)/1000f;
		currentTime = newTime;
	}
	public final long getTime(){
		return currentTime;
	}
	public final long getTimeDelta(){
		return deltaTime;
	}
	public final float getTimeDeltaSeconds(){
		return deltaTimeSeconds;
	}
	public abstract void update();
}
