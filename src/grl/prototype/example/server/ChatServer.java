package grl.prototype.example.server;

import grl.prototype.networking.Server;

public class ChatServer {
	public static void main(String[] args){
		new ChatServer();
	}
	public ChatServer(){
		Server server = new Server();
		//ServerConsole console = new ServerConsole(server);
		server.start();
	}
}
