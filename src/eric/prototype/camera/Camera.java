package eric.prototype.camera;
import org.lwjgl.util.vector.Vector3f;


public class Camera {
	public Vector3f position;
	public float yaw;
	public float pitch;
	public float roll;
	
	public Camera()
	{
		position = new Vector3f();
		position.x = 0;
		position.y = 0;
		position.z = -7;
		
		yaw = 0;
		pitch = 0;
		roll = 0;
	}
}
