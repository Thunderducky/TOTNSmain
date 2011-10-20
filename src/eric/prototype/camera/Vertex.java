package eric.prototype.camera;
import org.newdawn.slick.Color;




public class Vertex {
	public float x;
	public float y;
	public float z;
	public Color color;
	public Coordinate2D textureCoordinate;
	public Vertex(){}
	public Vertex(float _x, float _y, float _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
	public Vertex(float _x, float _y, float _z, Color _color)
	{
		x = _x;
		y = _y;
		z = _z;
		color = _color;
	}
}
