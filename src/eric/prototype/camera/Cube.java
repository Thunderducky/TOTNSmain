package eric.prototype.camera;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;



public class Cube {
	public ArrayList<Vertex> vertices;
	public Texture texture;
	public Cube() // Default Cube
	{
		vertices = new ArrayList<Vertex>();
		
		Color color = new Color(0.0f,1.0f,0.0f);				// Color Blue
		vertices.add(new Vertex( 1.0f, 1.0f,-1.0f, color));		// Top Right Of The Quad (Top)
		vertices.add(new Vertex(-1.0f, 1.0f,-1.0f, color));		// Top Left Of The Quad (Top)
		vertices.add(new Vertex(-1.0f, 1.0f, 1.0f, color));		// Bottom Left Of The Quad (Top)
		vertices.add(new Vertex( 1.0f, 1.0f, 1.0f, color));		// Bottom Right Of The Quad (Top)
		
		color = new Color(1.0f,0.5f,0.0f);						// Color Orange
		vertices.add(new Vertex( 1.0f,-1.0f, 1.0f, color));		// Top Right Of The Quad (Bottom)
		vertices.add(new Vertex(-1.0f,-1.0f, 1.0f, color));		// Top Left Of The Quad (Bottom)
		vertices.add(new Vertex(-1.0f,-1.0f,-1.0f, color));		// Bottom Left Of The Quad (Bottom)
		vertices.add(new Vertex( 1.0f,-1.0f,-1.0f, color));		// Bottom Right Of The Quad (Bottom)
		
		color = new Color(1.0f,0.0f,0.0f);						// Color Red
		vertices.add(new Vertex( 1.0f, 1.0f, 1.0f, color));		// Top Right Of The Quad (Front)
		vertices.add(new Vertex(-1.0f, 1.0f, 1.0f, color));		// Top Left Of The Quad (Front)
		vertices.add(new Vertex(-1.0f,-1.0f, 1.0f, color));		// Bottom Left Of The Quad (Front)
		vertices.add(new Vertex( 1.0f,-1.0f, 1.0f, color));		// Bottom Right Of The Quad (Front)
		
		color = new Color(1.0f,1.0f,0.0f);						// Color Yellow
		vertices.add(new Vertex( 1.0f,-1.0f,-1.0f, color));		// Top Right Of The Quad (Back)
		vertices.add(new Vertex(-1.0f,-1.0f,-1.0f, color));		// Top Left Of The Quad (Back)
		vertices.add(new Vertex(-1.0f, 1.0f,-1.0f, color));		// Bottom Left Of The Quad (Back)
		vertices.add(new Vertex( 1.0f, 1.0f,-1.0f, color));		// Bottom Right Of The Quad (Back)
		
		color = new Color(0.0f,0.0f,1.0f);						// Color Blue
		vertices.add(new Vertex(-1.0f, 1.0f, 1.0f, color));		// Top Right Of The Quad (Left)
		vertices.add(new Vertex(-1.0f, 1.0f,-1.0f, color));		// Top Left Of The Quad (Left)
		vertices.add(new Vertex(-1.0f,-1.0f,-1.0f, color));		// Bottom Left Of The Quad (Left)
		vertices.add(new Vertex(-1.0f,-1.0f, 1.0f, color));		// Bottom Right Of The Quad (Left)
		
		color = new Color(1.0f,0.0f,1.0f);						// Color Violet
		vertices.add(new Vertex( 1.0f, 1.0f,-1.0f, color));		// Top Right Of The Quad (Right)
		vertices.add(new Vertex( 1.0f, 1.0f, 1.0f, color));		// Top Left Of The Quad (Right)
		vertices.add(new Vertex( 1.0f,-1.0f, 1.0f, color));		// Bottom Left Of The Quad (Right)
		vertices.add(new Vertex( 1.0f,-1.0f,-1.0f, color));		// Bottom Right Of The Quad (Right)
	}
	public void ApplyTextureDefault()
	{
		
		int length = vertices.size();
		for(int i = 0; i < length; i+=4)
		{	float offset = 0.125f * i/4;
			vertices.get(i).textureCoordinate = new Coordinate2D(0 + offset, 0);
			vertices.get(i+1).textureCoordinate = new Coordinate2D(0.125f + offset,0);
			vertices.get(i+2).textureCoordinate = new Coordinate2D(0.125f + offset,1);
			vertices.get(i+3).textureCoordinate = new Coordinate2D(0 + offset,1);
		}
	}
}
