package grl.prototype.networking.server;

import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.RootMessageProcessorNode;
import grl.prototype.networking.Server;

public class ServerMessageProcessor extends RootMessageProcessorNode{
	Server server;
	public ServerMessageProcessor(Server server){
		this.server = server;
	}
}
