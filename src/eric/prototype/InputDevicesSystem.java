package eric.prototype;

public class InputDevicesSystem {
	InputDevicesSystemData systemData;
	InputDevicesAccessFactory access;
	KeyboardController keyControl;
	public InputDevicesSystem()
	{
		systemData = new InputDevicesSystemData();
		
	}
	public void Initialize()
	{
		access = new InputDevicesAccessFactory(systemData);
		keyControl = new KeyboardController(systemData);
		
	}
	public void Update()
	{
		keyControl.Update();
	}
	public InputDevicesAccessFactory Access()
	{
		return access;
	}
}
