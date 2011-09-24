package com.renderer;

import org.lwjgl.opengl.GL11;

import com.loader.Font;
import com.loader.Vertex;

public class FadedFont extends Font {

	float deltaAcc = 0;
	static final float TTL = 3; 
	float deltaDec = TTL;
	
	public FadedFont(Vertex position) {
		super(position);
		
	}

	public void draw(float delta)
	{
		
		if (deltaDec < 0)
			return;
		
		deltaAcc += delta;
		if (deltaAcc > 4)
			deltaDec -= delta;
		
		GL11.glColor4f(1,1,1,Math.max(0f, deltaDec/TTL));
		draw();
		GL11.glColor4f(1,1,1,1);
	}
}
