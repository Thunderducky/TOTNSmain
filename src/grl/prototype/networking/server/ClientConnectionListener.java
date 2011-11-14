package grl.prototype.networking.server;

public interface ClientConnectionListener {
	public void onClientConnect(String username);
	public void onClientDisconnect(String username);
}
