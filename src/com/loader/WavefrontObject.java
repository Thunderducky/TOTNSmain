package com.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;


import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Timer;

import com.linearoja.cm.Asset;
import com.parser.LineParser;
import com.parser.obj.ObjLineParserFactory;

public class WavefrontObject extends Asset{

	
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Vertex> normals = new ArrayList<Vertex>();
	private ArrayList<TextureCoordinate> textures = new ArrayList<TextureCoordinate>();
	private ArrayList<Face> faces = new ArrayList<Face>();

	private ObjLineParserFactory parserFactory ;
	private Hashtable<String, Material> materials = new Hashtable<String, Material>();
	
	// This variable is used for both parsing and rendition
	private Material currentMaterial;
	
	private String contextfolder ="" ;
	
	public double radius=0;
	
	public WavefrontObject(String fileName)
	{
		super(fileName);
		int lastSlashIndex = fileName.lastIndexOf('/');
		if ( lastSlashIndex != -1)
			this.contextfolder = fileName.substring(0,lastSlashIndex+1);
		
		lastSlashIndex = fileName.lastIndexOf('\\');
		if ( lastSlashIndex != -1)
			this.contextfolder = fileName.substring(0,lastSlashIndex+1);
		
		parse(fileName);
		
		calculateRadius();
	}

	
	private void calculateRadius() {
		double currentNorm = 0;
		for(Vertex vertex : vertices)
		{
			currentNorm = vertex.norm(); 
			if ( currentNorm> radius )
				radius = currentNorm;
		}
		
	}


	public String getContextfolder() {
		return contextfolder;
	}


	public void parse(String fileName)  
	{
		parserFactory = new ObjLineParserFactory(this);
		
		
		InputStream fileInput = this.getClass().getResourceAsStream(fileName);
		if (fileInput == null)
			// Could not find the file in the jar.
			try
			{
				File file = new File(fileName);
				if (file.exists())
					fileInput = new FileInputStream(file);
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		
		
		
		BufferedReader in = null;
		
		try 
		{
			in = new BufferedReader(new InputStreamReader(fileInput));
		
		
			String currentLine = null;
			while((currentLine = in.readLine()) != null)
				parseLine(currentLine);
				
			if (in != null)
				in.close();
		
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error reading file :'"+fileName+"'");
		}
		

		
		System.out.println("Loaded OBJ from file '"+fileName+"'");
		System.out.println(getVertices().size()+" vertices.");
		System.out.println(getNormals().size()+" normals.");
		System.out.println(getTextures().size()+" textures coordinates.");
		System.out.println(getFaces().size()+" faces.");
	}

	private void parseLine(String currentLine) {
		
		//System.out.println("Parsing line:"+lineCounter);
		if ("".equals(currentLine))
			return;
		
		LineParser parser = parserFactory.getLineParser(currentLine);
		parser.parse();
		parser.incoporateResults(this);
	}
	public int displayListId = 0; 
	public void render() {
		
		
		if (displayListId != 0)
		{
			GL11.glCallList(displayListId);
			return;
		}

		displayListId = GL11.glGenLists(1);
		
		GL11.glNewList(displayListId,GL11.GL_COMPILE);
		
			Face face = null;
			Material material = new Material("__non_existant");
			
			for (int i=0;i<getFaces().size();i++)
			{
				face = getFaces().get(i);
				
				//System.out.println(material.getTexture() + "-" + face.getMaterial().getTexture());
				if (!material.equals(face.getMaterial()))
				{
					// Set texture and setColor
					material = face.getMaterial();
					if (material.getTexture() != null)
					{
						GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getTexture().getTextureID());
					}
					else
						GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
					
					if (material.getKd() != null)
						GL11.glColor3f(material.getKd().getX(),material.getKd().getY(),material.getKd().getZ());
				}
				
				if (face.getType() == Face.GL_TRIANGLES)
					GL11.glBegin(GL11.GL_TRIANGLES);
				else
					GL11.glBegin(GL11.GL_QUADS);
				
				Vertex vertex = null;
				Vertex normal = null;
				TextureCoordinate textureCoo = null;
				for (int j=0;j<face.getVertices().length;j++)
				{
					vertex = face.getVertices()[j];
					if (j < face.getNormals().length &&  face.getNormals()[j] != null)
					{
						normal = face.getNormals()[j];
						GL11.glNormal3f(normal.getX(),normal.getY(),normal.getZ());
					}
					
					if (j < face.getTextures().length && face.getTextures()[j] != null )
					{
						textureCoo = face.getTextures()[j];
						GL11.glTexCoord2f(textureCoo.getU(),textureCoo.getV());
						//GL11.glTexCoord2f(textureCoo.getV(),textureCoo.getU());
					}
					
					GL11.glVertex3f(vertex.getX(),vertex.getY(),vertex.getZ());
				}
				GL11.glEnd(); 
			}
		GL11.glEndList();
	}

	public void setMaterials(Hashtable<String, Material> materials) {
		this.materials = materials;
	}

	public void setTextures(ArrayList<TextureCoordinate> textures) {
		this.textures = textures;
	}

	public ArrayList<TextureCoordinate> getTextures() {
		return textures;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}

	public ArrayList<Face> getFaces() {
		return faces;
	}

	public void setNormals(ArrayList<Vertex> normals) {
		this.normals = normals;
	}

	public ArrayList<Vertex> getNormals() {
		return normals;
	}

	public Hashtable<String, Material> getMaterials() {
		
		return this.materials;
	}

	public Material getCurrentMaterial() {
		return currentMaterial;
	}
	
	public void setCurrentMaterial(Material currentMaterial )
	{
		this.currentMaterial= currentMaterial;
	}
	
	
	
	
}
