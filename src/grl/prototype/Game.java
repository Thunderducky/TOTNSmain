package grl.prototype;

import grl.prototype.graphics.Drawable;
import grl.prototype.graphics.Renderer;
import grl.prototype.state.State;
import grl.prototype.state.Updateable;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;



public abstract class Game<T extends State> implements Updateable<T>,Drawable<T>{

	private Timer timer;
	private DisplayMode currentDisplayMode;

	protected Timer getTimer(){
		return timer;
	}
	public final void start() {
		timer = new Timer();
		setDisplayMode(new DisplayMode(800,600));

		while (!Display.isCloseRequested()) {
			int delta = timer.getDelta();
			getState().updateTime();
			pollInput(getState());
			update(getState());
			draw(getState());
			Display.update();
			//Display.sync(60);
			timer.updateFPS();
		}
		close();
		Display.destroy();
	}
	public final void setDisplayMode(DisplayMode mode){
		try {
			Display.destroy();
			Display.setDisplayMode(mode);
			Display.setVSyncEnabled(true);
			Display.create();
			Display.setVSyncEnabled(true);
			
			GL11.glViewport(0,0,mode.getWidth(),mode.getHeight());
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, mode.getWidth(), mode.getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			currentDisplayMode = mode;
			init();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public final DisplayMode getDisplayMode(){
		return currentDisplayMode;
	}
	private final DisplayMode[] getOrderedDisplayModes(){
		DisplayMode[] modes = null;
		try {
			modes = Display.getAvailableDisplayModes();
			Arrays.sort(modes, new Comparator<DisplayMode>(){
				@Override
				public int compare(DisplayMode arg0, DisplayMode arg1) {
					int area1 = arg0.getWidth()*arg0.getHeight();
					int area2 = arg1.getWidth()*arg1.getHeight();
					int bits1 = arg0.getBitsPerPixel();
					int bits2 = arg1.getBitsPerPixel();
					int freq1 = arg0.getFrequency();
					int freq2 = arg1.getFrequency();
					if(area1 > area2){
						return 1;
					}
					else if (area1 < area2){
						return -1;
					}
					else{
						if(bits1>bits2){
							return 1;
						}
						else if (bits1<bits2){
							return -1;
						}
						else{
							if(freq1>freq2){
								return 1;
							}
							else if(freq1<freq2){
								return -1;
							}
							else{
								return 0;
							}
						}
					}
				}
			});
			return modes;
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modes;
	}
	public DisplayMode chooseDisplayMode(){
		DisplayMode mode = null;
		DisplayMode[] modes = getOrderedDisplayModes();
		mode =(DisplayMode)JOptionPane.showInputDialog(null, "Choose Display Mode", "Choose Display Mode", JOptionPane.OK_CANCEL_OPTION, null, modes, modes[0]);

		return mode;
	}
	
	protected abstract T getState();
	public abstract void init();
	public abstract void update(T gameState);
	public abstract void draw(T gameState);
	public abstract void pollInput(T gameState);
	protected abstract void close();
}
