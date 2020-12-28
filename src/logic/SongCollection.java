package logic;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class SongCollection implements Serializable {
    protected ArrayList<Song> songs = new ArrayList<>();
    protected String name;
    protected transient BufferedImage image = null;

    public SongCollection(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public abstract void addSong(Song song);

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            updateImage();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "The specified song doesn't exist");
            return false;
        }
    }

    public void updateImage() {
        for (Song song : songs) {
            if (song.getImage() != null) {
                this.image = song.getImage();
                break;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SongCollection)) {
            return false;
        }
        if (name.equals(((SongCollection) obj).getName())) {
            return true;
        }
        return false;
    }
}
