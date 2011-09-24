package com.loader;

public class Timer {
	
	org.lwjgl.util.Timer animTimer = null;
	float lastTime;
	float deltaCounter=0;
	int frameCounter;
	public String fps = "";
	public float delta;
	
	public Timer()
	{
		 animTimer = new org.lwjgl.util.Timer();
		    lastTime = 0;
	}
	
	public void update() {
		org.lwjgl.util.Timer.tick();
		frameCounter++;
	    delta = animTimer.getTime() - lastTime;
	    lastTime = animTimer.getTime();
	 
	    // update the animation by delta seconds
	    deltaCounter+= delta;
	    if (deltaCounter >= 1f)
	    {
	    	fps = ""+(int) (frameCounter/deltaCounter);
	    	frameCounter=0;
	    	deltaCounter=0;
	    }
	}
	
	
}
