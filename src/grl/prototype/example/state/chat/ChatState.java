package grl.prototype.example.state.chat;

import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.state.Updateable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChatState implements Updateable<GameState>{
	private LinkedList<ChatMessage> messageHistory = new LinkedList<ChatMessage>();
	private String unsentMessage = "";
	private boolean visible = true;
	
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
	
	@Override
	public void update(GameState state) {
	}
}
