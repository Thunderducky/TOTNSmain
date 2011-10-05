package grl.prototype.engine;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public abstract class Game {

	private Timer timer;

	protected Timer getTimer(){
		return timer;
	}
	public void start() {
		timer = new Timer();
		setDisplayMode(new DisplayMode(800,600));
		init();
		while (!Display.isCloseRequested()) {
			int delta = timer.getDelta();
			Core.update(delta);
			Display.update();
			//Display.sync(60);
			timer.updateFPS();
		}

		Display.destroy();
	}
	public void init(){
		Core.initialize();
	}
	public void setDisplayMode(DisplayMode mode){
		try {
			Display.destroy();
			Display.setDisplayMode(mode);
			Display.setVSyncEnabled(true);
			Display.create();
			Display.setVSyncEnabled(true);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, mode.getWidth(), mode.getHeight(), 0, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private DisplayMode[] getOrderedDisplayModes(){
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
}
