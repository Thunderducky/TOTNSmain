package grl.prototype.example.client.messages;

import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.example.client.messages.chat.ChatOutProcessor;
import grl.prototype.example.state.GameState;
import grl.prototype.messaging.RootMessageProcessor;
import grl.prototype.networking.NetworkClient;

public class ClientOutMessageProcessor extends RootMessageProcessor{
	private GameState gameState;
	public ClientOutMessageProcessor(GameState state, NetworkClient client){
		gameState = state;
		this.registerMessageProcessor(new ChatOutProcessor(state.getChatState(),client));
	}
}
