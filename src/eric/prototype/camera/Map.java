package eric.prototype.camera;

public class Map {
	// 64 x 64 x 64
	public Cube[][][] MapCubes;
	
	public Map(int he)
	{
		MapCubes = new Cube[64][64][64];
	}	
}
