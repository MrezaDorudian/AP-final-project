package logic;

import javax.swing.*;

public class Playlist extends SongCollection implements Addable{
    public Playlist(String playlistName) {
        super(playlistName);
    }

    @Override
    public void addSong(Song song) {
        if (song != null && song.getMp3File() != null) {
            if (!songs.contains(song))
                songs.add(song);
            else
                JOptionPane.showMessageDialog(null, "Song already exists in the playlist");
            updateImage();
        } else
            JOptionPane.showMessageDialog(null, "Error occurred while adding");
    }

    public void addSongs(SongCollection songCollection) {
        if (songCollection != null) {
            for (Song song : songCollection.getSongs())
                addSongWithoutMessages(song);
            JOptionPane.showMessageDialog(null, "All new songs was added successfully!");
        }
    }

    private void addSongWithoutMessages(Song song) {
        if (song != null && song.getMp3File() != null) {
            if (!songs.contains(song))
                songs.add(song);
            updateImage();
        }
    }
}
