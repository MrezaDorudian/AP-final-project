package logic;

import gui.MusicTime;
import gui.South;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.io.*;

/**
 * this class handle all works with playing, pausing, resuming and seeking the music
 * and make the music player(specifically mp3) and stores the frames read by the player
 * as a field to itself as one frame is read the playedFrames increases until it reaches
 * the end of music
 */

public class Options implements Runnable {
    private FileInputStream input;
    private BufferedInputStream bufferedInput;
    private AdvancedPlayer player;
    private String address;
    private int playedFrames;
    private boolean isPaused;
    private JProgressBar seekBar;

    public Options(String address, JProgressBar seekBar) {
        this.seekBar = seekBar;
        this.address = address;
        isPaused = false;
        playedFrames = 1;
    }


    @Override
    public void run() {
        boolean isFileOpened = false;
        while (!isFileOpened) {
            try {
                input = new FileInputStream(new File(this.address));
                isFileOpened = true;
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Couldn't find the file of the song.");
                return;
            }
        }
        bufferedInput = new BufferedInputStream(input);
        try {
            player = new AdvancedPlayer(bufferedInput);
        } catch (JavaLayerException e) {
            JOptionPane.showMessageDialog(null, "FATAL ERROR");
        }
        try {
            while (player.play(1)) {
                long coefficient = Manager.getInstance().getNowPlayingSong().getMp3File().getFrameCount() / Manager.getInstance().getNowPlayingSong().getMp3File().getLengthInSeconds();
                Manager.getInstance().getMainFrame().getSouth().getTimeElapsed().setValue(playedFrames / (int) coefficient);
                Manager.getInstance().getMainFrame().getSouth().getElapsed().setText(Manager.getInstance().getMainFrame().getSouth().getTimeElapsed().toString() + "  ");
//                South.elapsed.setText(Integer.toString(playedFrames / (int)coefficient));//new
                if (playedFrames / (int) coefficient >= Manager.getInstance().getNowPlayingSong().getMp3File().getLengthInSeconds()) {
                    Manager.getInstance().getMainFrame().getSouth().getTimeElapsed().setValue((int) Manager.getInstance().getNowPlayingSong().getMp3File().getLengthInSeconds());
                    Manager.getInstance().getMainFrame().getSouth().getElapsed().setText(Manager.getInstance().getMainFrame().getSouth().getTimeElapsed().toString() + "  ");
                }
//                South.elapsed.setText(South.timeElapsed.toString() + " ");

                this.seekBar.setValue(playedFrames);
                playedFrames++;
                if (isPaused) {
                    synchronized (player) {
                        player.wait();
                    }
                }
            }
            playedFrames++;
            seekBar.setValue(playedFrames);
            if (seekBar.getValue() == seekBar.getMaximum()) {
                isPaused = true;
                Manager.getInstance().getMainFrame().getSouth().setPlaying(false);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlayedFrames() {
        return playedFrames;
    }

    public void pauseMusic() {
        isPaused = true;
    }

    public void resumeMusic() {
        isPaused = false;
        synchronized (player) {
            player.notifyAll();
        }
    }

    public void seek(int frame) {
        synchronized (player) {
            try {
                if (isPaused) {
                    resumeMusic();
                    input = new FileInputStream(new File(this.address));
                    bufferedInput = new BufferedInputStream(input);
                    player = new AdvancedPlayer(bufferedInput);
                    playedFrames = frame;
                    this.seekBar.setValue(playedFrames);
                    player.play(frame, frame + 1);
                    pauseMusic();
                } else {
                    input = new FileInputStream(new File(this.address));
                    bufferedInput = new BufferedInputStream(input);
                    player = new AdvancedPlayer(bufferedInput);
                    playedFrames = frame;
                    this.seekBar.setValue(playedFrames);
                    player.play(frame, frame + 1);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "can't open file, please try again!");
            } catch (JavaLayerException e) {
                JOptionPane.showMessageDialog(null, "FATAL ERROR");
            }
        }
    }


    public boolean isPaused() {
        return isPaused;
    }
}