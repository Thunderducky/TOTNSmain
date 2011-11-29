package grl.prototype.state;

public interface Modifiable<T extends State> extends Updateable<T>{
	public boolean isModified();
	public void clearModified();
}
