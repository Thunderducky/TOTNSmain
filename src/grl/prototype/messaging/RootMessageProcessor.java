package grl.prototype.messaging;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract class RootMessageProcessor extends MessageProcessorNode{
	private Queue<Message> messageQueue = new LinkedList<Message>();
	public synchronized void addMessage(Message message){
		synchronized(messageQueue){
			messageQueue.add(message);
		}
	}
	public int getMessageCount(){
		return messageQueue.size();
	}
	public void dispatchMessages(){
		LinkedList<Message> queue;
		synchronized(messageQueue){
			queue = new LinkedList<Message>(messageQueue);
			messageQueue.clear();
		}
		while(!queue.isEmpty()){
			processMessage(queue.poll());
		}
	}
}

