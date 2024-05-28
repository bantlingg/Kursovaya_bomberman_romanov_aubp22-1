

        package main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    AudioInputStream audioStream;
    private Clip clip;
    private FloatControl volumeControl;

    public void playMusic(String filepath,boolean c) {
        try {
            File audioFile = new File(filepath);
             audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (c) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Зацикливание воспроизведения
            }
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.close();
        }
        if (audioStream != null) {
            try {
                audioStream.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        volumeControl.setValue((max-min)*x+min);
    }
}