package com.linearoja.cm;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sound extends Asset implements Loadable {
	private Audio audioEffect;
	public Sound(String path) {
		super(path);
	}

	@Override
	public void loadData() {
		try {
			audioEffect = AudioLoader.getAudio(this.getExtension().toUpperCase(), ResourceLoader.getResourceAsStream(this.getFile().getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getAcceptedExtensions() {
		return new String[] {"ogg","xm","aif","wav"};
	}
	
	public Audio getAudio(){
		return audioEffect;
	}

}
