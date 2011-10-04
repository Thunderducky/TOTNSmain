package eric.prototype;

public class InputDevicesAccessFactory 
{
	InputDevicesSystemData data;
	
	InputDevicesAccessFactory(InputDevicesSystemData systemdata)
	{
		data = systemdata;
	}
	
	public KeyboardView GetKeyboardView()
	{
		return new KeyboardView(data);
	}
}
