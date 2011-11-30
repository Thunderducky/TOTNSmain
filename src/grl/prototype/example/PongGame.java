package grl.prototype.example;

import grl.prototype.Game;
import grl.prototype.example.client.graphics.ChatRenderer;
import grl.prototype.example.client.graphics.PongRenderer;
import grl.prototype.example.client.messages.ClientInMessageProcessor;
import grl.prototype.example.client.messages.ClientOutMessageProcessor;
import grl.prototype.example.client.messages.chat.BroadcastChatMessage;
import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.example.client.messages.pong.PongStateMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.example.state.pong.Paddle;
import grl.prototype.example.state.pong.Player;
import grl.prototype.graphics.Renderer;
import grl.prototype.networking.NetworkClient;
import grl.prototype.networking.client.Connection;
import grl.prototype.scripting.Console;
import grl.prototype.state.State;
import grl.prototype.state.Timer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.linearoja.Assets;

public class PongGame extends Game<GameState>{
	private static Connection connection;
	public static void main(String[] args){
		String server = Console.getText("Server: ");
		String username = Console.getText("Username: ");
		//String password = Console.getText("Password: ");
		//connection = new Connection(username,password,server,8888);
		connection = new Connection(username,"test",server,8888);
		PongGame chat = new PongGame();
		chat.start();
	}

	private GameState gameState;
	private NetworkClient client;
	private ClientInMessageProcessor inMessageProcessor;
	private ClientOutMessageProcessor outMessageProcessor;
	private List<Renderer<GameState>> renderers = new ArrayList<Renderer<GameState>>();

	//These are for the text input. Should be moved to another
	long lastDeleteTime = 0;

	@Override
	public void init() {
		/*GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
		 */
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
		GL11.glClearDepth(1);                                       

		GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		loadRenderers();
		loadData();
		System.out.println("Loading completed.");
	}

	private void loadData(){
		//Connection conn = new Connection("eyce9000","password","127.0.0.1",8888);
		gameState = new GameState(connection);
		inMessageProcessor = new ClientInMessageProcessor(gameState);
		client = new NetworkClient(connection,inMessageProcessor);
		outMessageProcessor = new ClientOutMessageProcessor(gameState,client);
	}
	private void loadRenderers(){
		renderers.add(new PongRenderer());
		renderers.add(new ChatRenderer());
		for(Renderer<GameState> renderer:renderers){
			renderer.init();
		}
	}
	@Override
	protected void close(){
		client.stop();
	}

	@Override
	public void update(GameState state) {
		gameState.update();
		outMessageProcessor.dispatchMessages();
		//if(state.getPongState().isModified())
		Timer pongTimer = gameState.getTimer("pong-update");
		if(pongTimer.difference(gameState) >10){
			client.sendMessage(new PongStateMessage(client.getConnection().getUsername(),gameState.getPongState()));
			pongTimer.reset(gameState);
		}
		

		
		for(Renderer<GameState> renderer:renderers){
			if(renderer.isEnabled())
				renderer.update(gameState);
		}
		
		gameState.getPongState().clearModified();
	}

	@Override
	public void draw(GameState state) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		for(Renderer<GameState> renderer:renderers){
			if(renderer.isEnabled())
				renderer.draw(gameState);
		}
	}

	@Override
	public void pollInput(GameState state) {
		
		inMessageProcessor.dispatchMessages();
		
		String inputLine = gameState.getChatState().getUnsentMessageText();
		if(Keyboard.isKeyDown(Keyboard.KEY_DELETE) || Keyboard.isKeyDown(Keyboard.KEY_BACK)){
			if((gameState.getTime()-lastDeleteTime)>100){
				if(inputLine.length()>0){
					inputLine = inputLine.substring(0, inputLine.length()-1);
					gameState.getChatState().setUnsentMessageText(inputLine);
				}
				lastDeleteTime = gameState.getTime();
			}
		}

		Player player = gameState.getPongState().getPlayer(client.getConnection().getUsername());
		if(player!=null)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				player.getPaddle().setRate(Paddle.MOVE_RATE);
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				player.getPaddle().setRate(-1*Paddle.MOVE_RATE);
			}
			else{
				player.getPaddle().setRate(0);
			}
		}
		while (Keyboard.next()) {
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey()==Keyboard.KEY_RETURN){
					//Line is done
					if(inputLine.length()>0){
						sendChatMessage();
					}
				}
				else if(Keyboard.getEventKey()==Keyboard.KEY_LSHIFT ||
						Keyboard.getEventKey()==Keyboard.KEY_RSHIFT ||
						Keyboard.getEventKey()==Keyboard.KEY_LCONTROL ||
						Keyboard.getEventKey()==Keyboard.KEY_RCONTROL ||
						Keyboard.getEventKey()==Keyboard.KEY_DELETE ||
						Keyboard.getEventKey()==Keyboard.KEY_BACK ||
						Keyboard.getEventKey() == Keyboard.KEY_LWIN ||
						Keyboard.getEventKey() == Keyboard.KEY_RWIN ||
						Keyboard.getEventKey() == Keyboard.KEY_LMETA ||
						Keyboard.getEventKey() == Keyboard.KEY_RMETA ||
						Keyboard.getEventKey() == Keyboard.KEY_DOWN ||
						Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					//nothing
				}
				else{
					inputLine += Keyboard.getEventCharacter();
					gameState.getChatState().setUnsentMessageText(inputLine);
				}
			}
		}
	}

	private void sendChatMessage(){
		String message = gameState.getChatState().getUnsentMessageText();
		gameState.getChatState().setUnsentMessageText("");

		outMessageProcessor.addMessage(new BroadcastChatMessage(gameState.getConnection().getUsername()
				,message,System.currentTimeMillis()));

	}

	@Override
	protected GameState getState() {
		return gameState;
	}

}
