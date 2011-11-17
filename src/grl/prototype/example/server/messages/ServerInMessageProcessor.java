package grl.prototype.example.server.messages;

import grl.prototype.example.server.messages.chat.ChatInProcessor;
import grl.prototype.example.server.messages.pong.PongInProcessor;
import grl.prototype.example.state.GameState;
import grl.prototype.messaging.MessageProcessorNode;
import grl.prototype.messaging.RootMessageProcessor;
import grl.prototype.networking.Server;

public class ServerInMessageProcessor extends RootMessageProcessor{
	Server server;
	public ServerInMessageProcessor(Server server, GameState state){
		this.server = server;
		this.registerMessageProcessor(new ChatInProcessor(server));
		this.registerMessageProcessor(new PongInProcessor(state));
	}
}
