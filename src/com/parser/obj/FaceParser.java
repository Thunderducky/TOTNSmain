package com.parser.obj;

import org.lwjgl.Sys;

import com.loader.Face;
import com.loader.TextureCoordinate;
import com.loader.Vertex;
import com.loader.WavefrontObject;
import com.parser.LineParser;

public class FaceParser extends LineParser {

	private Face face;
	private Vertex[] vertices;
	private Vertex[] normals ;
	private TextureCoordinate[] textures;
	private WavefrontObject object = null;
	
	public FaceParser(WavefrontObject object)
	{
		this.object = object;
	}
	
	@Override
	public void parse() {
		face = new Face();
		normals = new Vertex[3];
		textures  = new TextureCoordinate[3];
		switch (words.length)
		{
			case 4 : parseTriangles(); break;
			case 5 : parseQuad(); 	   break;
			default: throw new RuntimeException("Could not identify face around '"+object.getFaces()+1); 
		}
		
		
	}
	
	private void parseTriangles() {
		face.setType(Face.GL_TRIANGLES);
		parseLine(3);
	}

	private void parseLine(int vertexCount) {
		String[] rawFaces = null;
		int currentValue ;
		vertices = new Vertex[vertexCount];
		for (int i = 1 ; i <= vertexCount ; i++)
		{
			rawFaces = words[i].split("/");
			
			// v
			currentValue = Integer.parseInt(rawFaces[0]);
			vertices[i-1] = object.getVertices().get(currentValue-1);	// -1 because references starts at 1
			
			if (rawFaces.length == 1)
				continue;
			
			if (!"".equals(rawFaces[1]))
			{
				currentValue = Integer.parseInt(rawFaces[1]);
				//System.out.println(currentValue+" at line:"+lineCounter);
				if (currentValue <= object.getTextures().size())  // This is to compensate the fact that if no texture is in the obj file, sometimes '1' is put instead of 'blank' (we find coord1/1/coord3 instead of coord1//coord3 or coord1/coord3)
					textures[i-1] = object.getTextures().get(currentValue-1); // -1 because references starts at 1
				//System.out.print("indice="+currentValue+" ="+textures[i-1].getU() + ","+textures[i-1].getV());
				//System.out.println(textures[i-1].getU()+"-"+textures[i-1].getV());
			}

			
			currentValue = Integer.parseInt(rawFaces[2]);
			normals[i-1] = object.getNormals().get(currentValue-1); 	// -1 because references starts at 1
		}
		//System.out.println("");
	}

	private void parseQuad() {
		face.setType(Face.GL_QUADS);
		parseLine(4);
	}

	@Override
	public void incoporateResults(WavefrontObject wavefrontObject) {
		
		face.setNormals(this.normals);
		face.setVertices(this.vertices);
		face.setTextures(this.textures);
		if (wavefrontObject.getCurrentMaterial() != null)
		{
			face.setMaterial(wavefrontObject.getCurrentMaterial());
		}
		//System.out.println("Face:"+faceC + "='" + vertices[0].getX() +"," + vertices[0].getY() +"," +vertices[0].getZ());
		//System.out.println("Face:"+faceC + "='" + vertices[1].getX() +"," + vertices[1].getY() +"," +vertices[1].getZ());
		//System.out.println("Face:"+faceC + "='" + vertices[2].getX() +"," + vertices[2].getY() +"," +vertices[2].getZ());
		//if (faceC == 0) System.exit(0);
		//faceC++;
		wavefrontObject.getFaces().add(face);
		
	}
	
	static int faceC = 0;
}
