package grl.prototype.example.state.pong;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;
import grl.prototype.state.Updateable;

import java.io.Serializable;

public class Player implements Serializable,Modifiable<GameState>{
	public enum Number{One,Two};
	private String username;
	private Number number;
	private Paddle paddle;
	private int score = 0;
	
	private boolean modified;
	public Player(String username,Number number){
		this.paddle = new Paddle();
		this.username = username;
		this.number = number;
	}
	public Player(String username,Number number,Paddle paddle){
		this.username = username;
		this.number = number;
		this.paddle = paddle;
	}
	public Player(Player player){
		this.username = player.getUsername();
		this.number = player.getNumber();
		paddle = new Paddle();
		this.copyValues(player);
	}
	public Paddle getPaddle(){
		return paddle;
	}
	public String getUsername(){
		return username;
	}
	public Number getNumber(){
		return number;
	}
	public void setScore(int score){
		if(score!=this.score){
			this.score = score;
			modified = true;
		}
		
	}
	public int getScore(){
		return score;
	}
	public void copyValues(Player player){
		paddle = new Paddle(player.paddle);
		this.setScore(player.getScore());
		modified = player.modified;
	}
	@Override
	public void update(GameState state) {
		paddle.update(state);
	}
	@Override
	public boolean isModified() {
		return modified || paddle.isModified();
	}
	@Override
	public void clearModified() {
		paddle.clearModified();
		modified = false;
	}
}
