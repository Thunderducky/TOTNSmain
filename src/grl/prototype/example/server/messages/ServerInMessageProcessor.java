package grl.prototype.example.server.messages;

import grl.prototype.example.server.messages.chat.ChatInProcessor;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.RootMessageProcessor;
import grl.prototype.networking.Server;

public class ServerInMessageProcessor extends RootMessageProcessor{
	Server server;
	public ServerInMessageProcessor(Server server){
		this.server = server;
		this.registerMessageProcessor(new ChatInProcessor(server));
	}
}
