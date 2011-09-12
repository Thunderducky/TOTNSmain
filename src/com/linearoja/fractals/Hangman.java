package com.linearoja.fractals;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.linearoja.fractals.shapes.Shape;
import com.linearoja.fractals.shapes.Triangle;
import com.linearoja.fractals.transforms.Rotate;
import com.linearoja.fractals.transforms.Transform;
import com.linearoja.fractals.transforms.Translate;

public class Hangman extends Fractal {
	public ArrayList<Shape> shapes;
	
	private int lastDepth = -1;
	
	@Override
	public void draw(int depth) {
		if(depth!=lastDepth)
			recalculate(depth);

		GL11.glColor3f(0.5f,0.5f,1.0f);
		for(Shape s:shapes)
			s.draw();
	}
	
	private void recalculate(int depth){
		lastDepth = depth;
		shapes = new ArrayList<Shape>();
		Triangle start = new Triangle();
		shapes.add(start);
		for(int i=0; i<depth; i++){
			ArrayList<Shape> nextShapes = new ArrayList<Shape>(shapes.size()*3);
			for(Shape s: shapes){
				s.getTransform().scale(.5f);
				
				Shape temp = s.clone();
				temp.translate(-90,-90);
				nextShapes.add(temp); 
				
				temp = s.clone();
				temp.translate(90,-90);
				nextShapes.add(temp); 
				
				s.rotate(90).translate(90, 90);
				nextShapes.add(s); 
			}
			shapes = nextShapes;
		}
	}
}
