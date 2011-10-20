package eric.prototype.camera;

public class CameraController {
	public Camera camera;
	public CameraController(Camera _camera)
	{
		camera = _camera;
	}
	
	public void walkForward(float distance, float yaw)
	{
	    camera.position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
	    camera.position.z += distance * (float)Math.cos(Math.toRadians(yaw));
	}
	 
	//moves the camera backward relative to its current rotation (yaw)
	public void walkBackwards(float distance, float yaw)
	{
		camera.position.x += distance * (float)Math.sin(Math.toRadians(yaw));
		camera.position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	 
	//strafes the camera left relitive to its current rotation (yaw)
	public void strafeLeft(float distance, float yaw)
	{
		camera.position.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
		camera.position.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	 
	//strafes the camera right relitive to its current rotation (yaw)
	public void strafeRight(float distance, float yaw)
	{
		camera.position.x -= distance * (float)Math.sin(Math.toRadians(yaw+90));
		camera.position.z += distance * (float)Math.cos(Math.toRadians(yaw+90));
	}
}
