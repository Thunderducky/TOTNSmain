package grl.prototype.state;

import java.util.HashMap;

public abstract class State{
	private long currentTime;
	private long deltaTime = 0;
	private float deltaTimeSeconds = 0;
	private HashMap<String,Timer> timers = new HashMap<String,Timer>();
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
	public Timer getTimer(String key){
		Timer timer = timers.get(key);
		if(timer== null){
			timer = new Timer();
			timer.reset(this);
			timers.put(key, timer);
		}
		return timer;
	}
	public abstract void update();
}
