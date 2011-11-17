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
	
	public Player(String username,Number number,Paddle paddle){
		this.username = username;
		this.number = number;
		this.paddle = paddle;
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
	@Override
	public void update(GameState state) {
		paddle.update(state);
		modified = false;
	}
	@Override
	public boolean isModified() {
		return modified || paddle.isModified();
	}
}
