package punneria;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
private AudioInputStream getStream(File audioFile) {
	try {
		return AudioSystem.getAudioInputStream(audioFile);
	}catch(Exception e) {
		System.out.println("couldn't load audio file");
		return null;
	}
}
private Clip getClip() {
	try {
		return AudioSystem.getClip();
	}catch(Exception e) {
		return null;
	}
}
Clip clip = getClip();
public void setClipFile(File audioFile) {
	try {
		clip.close();
		clip.open(getStream(audioFile));
	}catch(Exception e) {
		
	}
}
public void play() {
	clip.start();
}
}
