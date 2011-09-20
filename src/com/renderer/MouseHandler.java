package com.renderer;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

public class MouseHandler {

	
	private ArrayList<MouseButtonHandler> buttonHandlers = new ArrayList<MouseButtonHandler>();
	private ArrayList<Boolean> buttonWasPressed = new ArrayList<Boolean>();
	
	public MouseHandler()
	{
		buttonHandlers.add(new MouseButtonRotation());
		buttonWasPressed.add(false);
		
		buttonHandlers.add(new MouseButtonZoom());
		buttonWasPressed.add(false);
		
		buttonHandlers.add(new MouseButtonZoom());
		buttonWasPressed.add(false);
	}
	
	public void update()
	{
		MouseButtonHandler buttonHandler = null;
		for (int i =0 ; i< buttonHandlers.size(); i++)
		{
			buttonHandler = buttonHandlers.get(i);
			if (buttonHandler == null)
				continue;
			if (!Mouse.isButtonDown(i))
			{
				if (buttonWasPressed.get(i))
					buttonHandler.justReleased();

				else
					buttonHandler.up();
				buttonWasPressed.set(i,false);
			}
			else
			{
				if (Mouse.isButtonDown(0))
					if (buttonWasPressed.get(i))
						buttonHandler.down();
					else
					{
						buttonWasPressed.set(i,true);
						buttonHandler.justPressed();
					}
			}
		}
			
		
	}
	
	
		
		
	
}
