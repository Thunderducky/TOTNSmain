package grl.prototype.example;

import grl.prototype.Game;
import grl.prototype.example.client.graphics.ChatRenderer;
import grl.prototype.example.client.messages.ClientInMessageProcessor;
import grl.prototype.example.client.messages.ClientOutMessageProcessor;
import grl.prototype.example.client.messages.chat.BroadcastChatMessage;
import grl.prototype.example.client.messages.chat.ChatInProcessor;
import grl.prototype.example.client.messages.chat.ChatMessage;
import grl.prototype.example.state.GameState;
import grl.prototype.graphics.Renderer;
import grl.prototype.networking.NetworkClient;
import grl.prototype.networking.client.Connection;
import grl.prototype.scripting.Console;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.linearoja.Assets;

public class ChatGame extends Game{
	private static Connection connection;
	public static void main(String[] args){
		String server = Console.getText("Server: ");
		String username = Console.getText("Username: ");
		String password = Console.getText("Password: ");
		connection = new Connection(username,password,server,8888);
		//connection = new Connection("eyce9000","test","127.0.0.1",8888);
		ChatGame chat = new ChatGame();
		chat.start();
	}
	
	private GameState gameState;
	private NetworkClient client;
	private ClientInMessageProcessor inMessageProcessor;
	private ClientOutMessageProcessor outMessageProcessor;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	
	@Override
	protected void init() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
		GL11.glClearDepth(1);                                       

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		loadData();
		loadRenderers();
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
		renderers.add(new ChatRenderer());
		for(Renderer renderer:renderers){
			renderer.init();
		}
	}

	@Override
	protected void update(int delta) {
		gameState.update();
		// TODO Auto-generated method stub
		outMessageProcessor.dispatchMessages();
		inMessageProcessor.dispatchMessages();
		for(Renderer renderer:renderers){
			renderer.update(gameState);
		}
	}

	@Override
	protected void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

		for(Renderer renderer:renderers){
			renderer.draw(gameState);
		}
	}

	@Override
	protected void pollInput(int delta) {
		// TODO Auto-generated method stub
		String inputLine = gameState.getChatState().getUnsentMessageText();
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
						Keyboard.getEventKey()==Keyboard.KEY_RCONTROL)
				{
					//nothing
				}
				else if(Keyboard.getEventKey()==Keyboard.KEY_DELETE ||
						Keyboard.getEventKey()==Keyboard.KEY_BACK){
					inputLine = inputLine.substring(0, inputLine.length()-1);
					gameState.getChatState().setUnsentMessageText(inputLine);
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

		System.out.println(message);
		outMessageProcessor.addMessage(new BroadcastChatMessage(gameState.getConnection().getUsername()
				,message,System.currentTimeMillis()));
		
	}

}
