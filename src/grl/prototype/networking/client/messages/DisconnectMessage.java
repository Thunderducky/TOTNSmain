package grl.prototype.networking.client.messages;

import grl.prototype.networking.client.Connection;

public class DisconnectMessage extends ClientMessage{
	public DisconnectMessage(Connection conn, int version){
		super(conn.getUsername(),conn.getPassword(),version);
	}
	public DisconnectMessage(String username, String password, int version){
		super(username,password,version);
	}
}