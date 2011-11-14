package grl.prototype.networking.client.messages;

import grl.prototype.networking.client.Connection;

public class ConnectMessage extends ClientMessage{
	public ConnectMessage(Connection conn, int version){
		super(conn.getUsername(),conn.getPassword(),version);
	}
	public ConnectMessage(String username, String password,int version){
		super(username,password,version);
	}
}
