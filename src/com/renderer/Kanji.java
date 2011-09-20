package com.renderer;

import org.lwjgl.opengl.GL11;

import com.loader.Texture;
import com.loader.TextureLoader;
import com.loader.Vertex;

public class Kanji {

	private Texture[] kanjis  ;
	private Texture defTexture=  null;
	
	public Kanji()
	{
		if (kanjis == null)
			loadTextures();
	}
	
	private void loadTextures()
	{
		kanjis = new Texture[4];
		
		kanjis[0] = TextureLoader.instance().loadTexture("/Data/eternal.png");
		kanjis[1] = TextureLoader.instance().loadTexture("/Data/not.png");
		kanjis[2] = TextureLoader.instance().loadTexture("/Data/give.png");
		kanjis[3] = TextureLoader.instance().loadTexture("/Data/up.png");
		
		defTexture = kanjis[0];
	} 
	
	public void render()
	{
		
		
		GL11.glPushMatrix();
			GL11.glTranslatef(Starter.SCREEN_WIDTH/2 - Starter.SCREEN_WIDTH/2/10,Starter.SCREEN_HEIGHT/2 - Starter.SCREEN_HEIGHT/2/10, 0);
			for (int i=0;i<kanjis.length ; i++)
			{
				drawKanji(i);
				GL11.glTranslatef(0,-(defTexture.getHeight()/4+defTexture.getHeight()/2),0);
			}
		
		GL11.glPopMatrix();
	}
	
	private void drawKanji(int i)
	{
		Texture currentKani = kanjis[i];
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, currentKani.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(1,1); //Upper right
			GL11.glVertex2f(currentKani.getWidth()/4, -currentKani.getHeight()/4);
		
			GL11.glTexCoord2f(1,0); // Lower right
			GL11.glVertex2f(currentKani.getWidth()/4,currentKani.getHeight()/4);
			
			GL11.glTexCoord2f(0,0); //Lower left
			GL11.glVertex2f(-currentKani.getWidth()/4,currentKani.getHeight()/4);
			
			
			GL11.glTexCoord2f(0,1); //Upper left			
			GL11.glVertex2f(-currentKani.getWidth()/4, -currentKani.getHeight()/4);        
	
		}
		GL11.glEnd();
	}
	
}
