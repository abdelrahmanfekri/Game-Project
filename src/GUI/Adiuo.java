package GUI;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Adiuo {
     public Adiuo(String adu,int loop) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
    		File f = new File(adu).getAbsoluteFile();
    		AudioInputStream music = AudioSystem.getAudioInputStream(f);
			Clip clip =AudioSystem.getClip();
			clip.open(music);
			clip.loop(loop);
			clip.start();
     }
}
