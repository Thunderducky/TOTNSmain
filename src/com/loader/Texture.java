package com.loader;


import java.nio.IntBuffer;
 
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
 
/**
 * $Id$
 * <p>
 * Simple Texture containing the texture ID generated by OGL
 * and various attributes. Can be called upon to bind and paint itself.
 * <br/>
 * This is a modified version of the Texture class from: org.lwjgl.examples.spaceinvaders.Texture
 * </p>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class Texture {
 
  /** Texture id for this image (OpenGL) */
  private int textureID;
 
  /** Width of this image */
  private int width;
 
  /** Height of this image */
  private int height;
 
  /** Width ratio */
  private float	widthRatio;
 
  /** Height ratio */
  private float	heightRatio;
 
  /** Texture width */
  private int textureWidth;
 
  /** Texture heigth */
  private int textureHeigth;
 

  
  /**
   * Creates a new Texture
   * 
   * @param textureID Texture ID
   * @param width Width of image
   * @param height Height of image
   */
  public Texture(int textureID, int width, int height) {
    this(textureID, width, height, 1.0f, 1.0f, width, height);
  }
 
  /**
   * Creates a new Texture
   * 
   * @param textureID Texture ID
   * @param width Width of image
   * @param height Height of image
   * @param widthRatio Ratio of texture width
   * @param heightRatio Ratio of texture height
   * @param textureWidth Actual width of texture
   * @param textureHeight Actual height of texture
   */
  public Texture(int textureID, int width, int height, float widthRatio, float heightRatio, int textureWidth, int textureHeight) {
    this.textureID = textureID;
    this.width = width;
    this.height = height;
    this.widthRatio = widthRatio;
    this.heightRatio = heightRatio;
    this.textureWidth = textureWidth;
    this.textureHeigth = textureHeight;
  }
 
  /**
   * Destroys this Texture, reclaiming all resources
   */
  public void destroy() {
    IntBuffer scratch = BufferUtils.createIntBuffer(1);
    scratch.put(0, textureID);
    GL11.glDeleteTextures(scratch);
  }
 
  /**
   * @return Texture ID for this image
   */
  public int getTextureID() {
    return textureID;
  }
  /**
   * @return height of image
   */
  public int getHeight() {
    return height;
  }
  /**
   * @return width of image
   */
  public int getWidth() {
    return width;
  }
  /**
   * Binds this image
   */
  public void bind() {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
  }
  /**
   * Renders this image to a quad. The image will bind itself first.
   */
  public void render() {
	  int ratio = 40;
    bind();
    GL11.glBegin(GL11.GL_QUADS);
    {
      GL11.glTexCoord2f(0.0f, 0.0f);              GL11.glVertex2i(0, 0);
      GL11.glTexCoord2f(widthRatio, 0.0f);        GL11.glVertex2i(width/ratio, 0);
      GL11.glTexCoord2f(widthRatio, heightRatio); GL11.glVertex2i(width/ratio, height/ratio);
      GL11.glTexCoord2f(0.0f, heightRatio);       GL11.glVertex2i(0, height/ratio);
    }
    GL11.glEnd();
  }
  /**
   * Returns a string representation of the image
   */
  public String toString() {
    return "Texture [" + textureID + ", " + width + ", " + height + ", " + 
    textureWidth + ", " + textureHeigth + ", " + widthRatio + ", " + heightRatio + "]";
  }
}