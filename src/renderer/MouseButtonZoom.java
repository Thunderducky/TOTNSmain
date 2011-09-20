package com.renderer;

import org.lwjgl.input.Mouse;

public class MouseButtonZoom implements MouseButtonHandler {

	public void justReleased() {
		Mouse.setGrabbed(false);

	}

	public void justPressed() {
		Mouse.setGrabbed(true);
	}

	public void down() {
		
		Starter.viewRadius += Mouse.getDY();
		if (Starter.viewRadius > 0)
			Starter.viewRadius =0;
		//System.out.println(Starter.viewRadius);

	}

	public void up() {
		// TODO Auto-generated method stub

	}

}
