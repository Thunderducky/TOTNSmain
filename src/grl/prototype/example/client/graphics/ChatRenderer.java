package grl.prototype.example.client.graphics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.linearoja.Assets;

import grl.prototype.animation.Animation;
import grl.prototype.animation.interpolate.Interpolator;
import grl.prototype.animation.interpolate.LinearInterpolationFunction;
import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.chat.ChatState;
import grl.prototype.graphics.Renderer;

public class ChatRenderer implements Renderer<GameState> {
	TrueTypeFont font;
	private int lastChatCount = 0;
	private int yOffset = 0;
	private Animation verticalAnimation;
	private LinkedList<ChatMessage> historyLines = new LinkedList<ChatMessage>();
	private String currentText = "";
	private int lineCount = 10;
	int fontHeight;
	@Override
	public void init(){
		Assets.fonts.BleedingCowboys.loadData();
		font = Assets.fonts.BleedingCowboys.getFont(16);
		fontHeight = font.getHeight();
	}
	@Override
	public void update(GameState gameState) {
		ChatState chatState = gameState.getChatState();
		List<ChatMessage> chat = gameState.getChatState().getMessageHistoryCopy();
		currentText = chatState.getUnsentMessageText();

		if(lastChatCount < chat.size()){
			//The count has changed!
			lastChatCount = chat.size();
			historyLines = new LinkedList<ChatMessage>();
			if(chat.size() <= lineCount){
				historyLines.addAll(chat);
			}
			else{
				for(int i=chat.size()-1; i>chat.size()-lineCount-1; i--){
					historyLines.push(chat.get(i));
				}
				verticalAnimation = new Animation(new Interpolator(){
					@Override
					public void interpolate(double t) {
						yOffset = (int)((0-t)*fontHeight);
					}

					@Override
					public void onStart() {
						yOffset = 0;
					}
					@Override
					public void onComplete() {
						verticalAnimation = null;
					}

				},new LinearInterpolationFunction(300));
				verticalAnimation.start(gameState);
			}
		}

		if(verticalAnimation!=null){
			verticalAnimation.update(gameState);
		}
	}
	@Override
	public void draw(GameState gameState) {
		LinkedList<ChatMessage> chat = new LinkedList<ChatMessage>(historyLines);
		for(int i=0;i<=lineCount; i++){
			ChatMessage message = chat.poll();
			if(message!=null){
				Color color = Color.green;
				if(message.getFrom().equals(gameState.getConnection().getUsername())){
					color = Color.white;
				}
				font.drawString(0, fontHeight*i+yOffset, message.toString(),color);
			}
		}

		font.drawString(0, fontHeight*(lineCount+1)+yOffset, currentText);

	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub

	}

}
