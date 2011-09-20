package com.renderer;

import org.lwjgl.input.Mouse;

public class MouseButtonRotation implements MouseButtonHandler {

	private int startX=0;
	private int startY=0;
	
	public void justReleased() {
		Mouse.setGrabbed(false);

	}

	public void justPressed() {
		Mouse.setGrabbed(true);
		startX = Mouse.getX();
		startY = Mouse.getY();
	}

	public void down() {
		int dX = startX - Mouse.getX();
		int dY = startY - Mouse.getY();
		
		startX = Mouse.getX();
		startY = Mouse.getY();
		
		
		Starter.viewAngleX = Math.min(90,Starter.viewAngleX + dY);
		/*
		if (Starter.viewAngleX > 90)
			Starter.viewAngleX = 90;
		if ((Starter.viewAngleX < -90))
			Starter.viewAngleX = -90;
		*/
		
		Starter.viewAngleY += dX;

	}

	public void up() {
//		 TODO Auto-generated method stub

	}

}
