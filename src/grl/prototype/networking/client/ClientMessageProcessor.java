package grl.prototype.networking.client;

import grl.prototype.messaging.RootMessageProcessorNode;

public class ClientMessageProcessor extends RootMessageProcessorNode{
	public ClientMessageProcessor(){
		this.registerMessageProcessor(new ChatProcessor());
	}
}
