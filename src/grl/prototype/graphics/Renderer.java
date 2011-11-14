package grl.prototype.graphics;

import grl.prototype.example.state.GameState;

public interface Renderer {
	public void init();
	public void update(GameState gameState);
	public void draw(GameState gameState);
}
