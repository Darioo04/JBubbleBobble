package controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {

    private static AudioManager instance;
    private String path;
    private Clip clip;
    private Clip backgroundClip;
    private Clip levelClip;
    private FloatControl gainControl;


    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Vado a ottenere il percorso assoluto del progetto perché a differenza di quanto accade con le immagini,
     * non posso accedere direttamente alla cartella res del progetto con il metodo getResourceAsStream().
     * In questo modo la cartella con i suoni viene trovata a prescindere dall'utente che esegue il progetto
     */
    private AudioManager() {
        String projectPath = System.getProperty("user.dir");
        path = projectPath + "/res/Sounds/";
    }

    public void playBackgroundMusic(String filename){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path+filename+".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioIn);
            gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(0.3f);
            backgroundClip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        }
    }
    public void stopBackgroundMusic(){
        if(backgroundClip != null) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }
    public void pauseBackgroundMusic(){
        if(backgroundClip != null) backgroundClip.stop();
    }
    public void resumeBackgroundMusic() {
        if(backgroundClip != null) backgroundClip.start();
    }
    
    public void playLevelMusic(String filename){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path+filename+".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            levelClip = AudioSystem.getClip();
            levelClip.open(audioIn);
            gainControl = (FloatControl) levelClip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(0.3f);
            levelClip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        }
    }
    public void stopLevelMusic(){
        if(levelClip != null) {
        	levelClip.stop();
        	levelClip.close();
        }
    }
    public void pauseLevelMusic(){
        if(levelClip != null) levelClip.stop();
    }
    public void resumeLevelMusic() {
        if(levelClip != null) levelClip.start();
    }

    public void play(String filename) {

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path+filename+".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        }

    }
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
    
    public void setVolume(float volume) {
        if (gainControl != null) {
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

}