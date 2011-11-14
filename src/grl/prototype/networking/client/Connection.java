package grl.prototype.networking.client;

public class Connection {
	private String username;
	private String password;
	private String serverAddress;
	private int socket;
	public Connection(String username, String password, String server, int socket){
		this.username = username;
		this.password = password;
		this.serverAddress = server;
		this.socket = socket;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getServerAddress(){
		return serverAddress;
	}
	public int getSocket(){
		return socket;
	}
}
