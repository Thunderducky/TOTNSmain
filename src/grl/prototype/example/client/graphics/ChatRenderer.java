package grl.prototype.example.client.graphics;

import java.util.Queue;

import org.newdawn.slick.Color;

import com.linearoja.Assets;

import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.chat.ChatState;
import grl.prototype.graphics.Renderer;

public class ChatRenderer implements Renderer {
	@Override
	public void init(){
		Assets.fonts.BleedingCowboys.loadData();
	}
	@Override
	public void draw(GameState state) {
		ChatState chatState = state.getChatState();
		Queue<ChatMessage> chat = state.getChatState().getMessageHistoryCopy();
		
		for(int i=0;i<11; i++){
			ChatMessage message = chat.poll();
			if(message!=null){
				Color color = Color.green;
				if(message.getFrom().equals(state.getConnection().getUsername())){
					color = Color.white;
				}
				String text = message.getFrom()+": "+message.getText();
				Assets.fonts.BleedingCowboys.getFont().drawString(0, 24*i, text,color);
			}
		}
		
		Assets.fonts.BleedingCowboys.getFont().drawString(0, 24*11, chatState.getUnsentMessageText());
	}

}
