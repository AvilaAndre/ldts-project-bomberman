package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;


public class MusicPlayer {
    private final Clip backgroundMusic;
    private final Clip explosionSound1;
    private final Clip explosionSound2;

    public MusicPlayer() {
        this.backgroundMusic = loadMusic("/music/BombermanMusic.wav");
        this.explosionSound1 = loadMusic("/soundfx/explosion.wav");
        this.explosionSound2 = loadMusic("/soundfx/explosion2.wav");
    }

    private Clip loadMusic(String filePath_) throws NullPointerException{
        try {
            File musicFile = new File(MusicPlayer.class.getResource(filePath_).getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playExplosionSound1() {
        explosionSound1.setMicrosecondPosition(0);
        explosionSound1.start();
        explosionSound1.loop(0);
    }

    public void playExplosionSound2() {
        explosionSound2.setMicrosecondPosition(0);
        explosionSound2.start();
        explosionSound2.loop(0);
    }
}

