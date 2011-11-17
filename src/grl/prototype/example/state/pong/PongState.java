package grl.prototype.example.state.pong;

import java.io.Serializable;

import grl.prototype.example.state.GameState;
import grl.prototype.state.Modifiable;
import grl.prototype.state.Updateable;

public class PongState implements Modifiable<GameState>,Serializable{
	private Player player1, player2;
	private Ball ball;
	private boolean active;
	public PongState(){
		player1 = new Player("p1",Player.Number.One,new Paddle());
		player2 = new Player("p2",Player.Number.Two,new Paddle());
		active = true;
		ball = new Ball();
	}
	public PongState(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		ball = new Ball();
		active = true;
	}
	public boolean gameIsStarted(){
		return active;
	}
	public boolean gameIsComplete(){
		if(this.player1.getScore()>=10 || this.player2.getScore()>=10){
			return true;
		}
		return false;
	}
	public Player getPlayer(Player.Number number){
		switch(number){
		case One:
			return player1;
		case Two:
			return player2;
		}
		return null;
	}
	public Player getPlayer(String username){
		if(player1.getUsername().equals(username)){
			return player1;
		}
		else if(player2.getUsername().equals(username)){
			return player2;
		}
		return null;
	}
	public Ball getBall(){
		return ball;
	}
	@Override
	public void update(GameState state) {
		if(active){
//			Paddle p1 = player1.getPaddle();
//			p1.setY(p1.getY()+p1.getRate()*state.getTimeDeltaSeconds());
//			
//			Paddle p2 = player2.getPaddle();
//			p2.setY(p2.getY()+p2.getRate()*state.getTimeDeltaSeconds());
			player1.update(state);
			player2.update(state);
		}
	}
	@Override
	public boolean isModified() {
		return ball.isModified() || player1.isModified() || player2.isModified();
	}
}
