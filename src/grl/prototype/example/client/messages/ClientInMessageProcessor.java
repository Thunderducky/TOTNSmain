package grl.prototype.example.client.messages;

import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.example.client.messages.pong.PongInProcessor;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.chat.ChatState;
import grl.prototype.messaging.RootMessageProcessor;

public class ClientInMessageProcessor extends RootMessageProcessor{
	public ClientInMessageProcessor(GameState state){
		this.registerMessageProcessor(new ChatInProcessor(state.getChatState()));
		this.registerMessageProcessor(new PongInProcessor(state));
	}
}
