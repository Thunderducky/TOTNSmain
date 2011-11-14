package grl.prototype.example.state.chat;

import grl.prototype.example.client.messages.chat.ChatMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChatState {
	private LinkedList<ChatMessage> messageHistory = new LinkedList<ChatMessage>();
	private String unsentMessage = "";
	
	public void setUnsentMessageText(String message){
		this.unsentMessage = message;
	}
	public String getUnsentMessageText(){
		return unsentMessage;
	}
	
	public void addMessage(ChatMessage message){
		synchronized(messageHistory){
			messageHistory.add(message);
		}
	}
	
	public List<ChatMessage> getMessageHistoryCopy(){
		synchronized(messageHistory){
			return new LinkedList(messageHistory);
		}
	}
}
