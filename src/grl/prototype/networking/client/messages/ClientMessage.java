package grl.prototype.networking.client.messages;

import java.util.List;
import java.util.Map.Entry;
import grl.prototype.messaging.Message;

public abstract class ClientMessage extends Message {
	String username;
	String password;
	int version;
	public ClientMessage(String username, String password, int version){
		this.username = username;
		this.password = password;
		this.version = version;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public int getVersion(){
		return version;
	}
}
