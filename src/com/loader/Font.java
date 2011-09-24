package com.loader;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;



	
public class Font {
	
	protected Texture[] animationTextures = null;
	public int original_width;
	public int original_height;
	private float ratio = 1f;
	public float width ;
	public float height ;
	private String string = null;
	
	public static final int LEFT_TO_RIGHT = 1;
	public static final int RIGHT_TO_LEFT = -1;
	private int sens;
	
	public void init()
	{
		if (this.animationTextures == null)
			this.animationTextures = TextureLoader.instance().loadAnimation("Data/Fonts.png",16,16,32,32);
		this.original_width = animationTextures[0].getWidth();
		this.original_height = animationTextures[0].getHeight();
		this.width = this.original_width;
		this.height = this.original_height;
	}

	public void setRatio(float newRatio)
	{
		this.ratio = newRatio;
		this.width = this.original_width * ratio;
		this.height = this.original_height * ratio;
	}
	
	private Vertex position;
	
	public Font(Vertex position)
	{
		this.position = position;
		init();
		sens = LEFT_TO_RIGHT;
		setRatio(0.5f);
	}

	
public void setString(String string)
{
	this.string = string;
	chars = this.string.toCharArray();
}


private char[] chars ;


public void draw()
{
   	GL11.glPushMatrix();
	   	GL11.glTranslatef(position.getX(),position.getY(),position.getZ());
	   	
	   	if (this.sens == LEFT_TO_RIGHT)
	   	{
	   		for (int i=0;i<chars.length;i++)
			{
	   			
				drawChar(i);
				GL11.glTranslatef(this.sens*(this.width+this.width/3),0,0);
			}
	   	}
	   	else
	   	{
	   		for (int i=chars.length-1; i>=0;i--)
			{
				drawChar(i);
				GL11.glTranslatef(this.sens*(this.width+this.width/3),0,0);
			}
	   	}
	   		
		
	GL11.glPopMatrix();
	
}


private void drawChar(int i)
{
	
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.animationTextures[chars[i]].getTextureID());
	GL11.glBegin(GL11.GL_QUADS);
	{
		GL11.glTexCoord2f(1,1); //Upper right
		GL11.glVertex2f(width, -height);
	
		GL11.glTexCoord2f(1,0); // Lower right
		GL11.glVertex2f(width,height);
		
		GL11.glTexCoord2f(0,0); //Lower left
		GL11.glVertex2f(-width,height);
		
		
		GL11.glTexCoord2f(0,1); //Upper left			
		GL11.glVertex2f(-width, -height);        
   
			
	
			
	}
	GL11.glEnd();
}

public void setSens(int sens) {
	this.sens = sens;
	
}

}
