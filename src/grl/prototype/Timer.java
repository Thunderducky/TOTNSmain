package grl.prototype;

import org.lwjgl.Sys;

public class Timer {
	/** time at last frame */
	private long lastFrame;
	
	/** frames per second */
	private int fps;
	/** last fps time */
	private long lastFPS;
	
	private int currentFPS;
	
	public Timer(){
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime();
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	 
	    return delta;
	}
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			currentFPS = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	public int getFPS(){
		return currentFPS;
	}
}
