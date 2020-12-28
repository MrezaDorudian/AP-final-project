package gui;

import javazoom.jl.player.Player;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterCenterCenter extends JPanel {

    private ArrayList<Addable> addables = new ArrayList<>();
    private Playlist playlist = null;

    public CenterCenterCenter() {
        Color myGray = new Color(50,50,50);
        setBackground(myGray);
        setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT, 1, 1));
    }

    public void refreshSearchResults(Playlist playlist, String searchedText) {
        removeAll();
        if (addables.size() >= 1) {
            if (addables.get(0) instanceof Song) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        if (((Song) addables.get(i)).getSongName().contains(searchedText) || ((Song) addables.get(i)).getAlbumName().contains(searchedText) || ((Song) addables.get(i)).getArtistName().contains(searchedText)) {
                            WindowElement windowElement = new WindowElement((Song) addables.get(i));
                            add(windowElement);
                            if (playlist != null)
                                windowElement.setPlaylist((Song) addables.get(i), playlist);
                        }
                    } else {
                        if (((Song) addables.get(i)).getSongName().contains(searchedText) || ((Song) addables.get(i)).getAlbumName().contains(searchedText) || ((Song) addables.get(i)).getArtistName().contains(searchedText)) {
                            ListElement listElement = new ListElement((Song) addables.get(i));
                            add(listElement);
                            if (playlist != null)
                                listElement.setPlaylist((Song) addables.get(i), playlist);
                        }
                    }
                }
            } else if (addables.get(0) instanceof Album) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        if (((Album) addables.get(i)).getName().contains(searchedText) || ((Album) addables.get(i)).getArtistName().contains(searchedText))
                            add(new WindowElement((Album) addables.get(i)));
                    } else {
                        if (((Album) addables.get(i)).getName().contains(searchedText) || ((Album) addables.get(i)).getArtistName().contains(searchedText))
                            add(new ListElement((Album) addables.get(i)));
                    }
                }
            } else if (addables.get(0) instanceof Playlist) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        if (((Playlist) addables.get(i)).getName().contains(searchedText))
                            add(new WindowElement((Playlist) addables.get(i)));
                    } else {
                        if (((Playlist) addables.get(i)).getName().contains(searchedText))
                            add(new ListElement((Playlist) addables.get(i)));
                    }
                }
            }
        }
        this.playlist = playlist;
        setVisible(false);
        setVisible(true);
    }

    public void refresh(Playlist playlist) {
        removeAll();
        if (addables.size() >= 1) {
            if (addables.get(0) instanceof Song) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        WindowElement windowElement = new WindowElement((Song) addables.get(i));
                        add(windowElement);
                        if (playlist != null)
                            windowElement.setPlaylist((Song) addables.get(i), playlist);
                    } else {
                        ListElement listElement = new ListElement((Song) addables.get(i));
                        add(listElement);
                        if (playlist != null)
                            listElement.setPlaylist((Song) addables.get(i), playlist);
                    }
                }
            } else if (addables.get(0) instanceof Album) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        add(new WindowElement((Album) addables.get(i)));
                    } else {
                        add(new ListElement((Album) addables.get(i)));
                    }
                }
            } else if (addables.get(0) instanceof Playlist) {
                for (int i = 0; i < addables.size(); i++) {
                    if (!Manager.getInstance().getMainFrame().getSouth().isListed()) {
                        add(new WindowElement((Playlist) addables.get(i)));
                    } else {
                        add(new ListElement((Playlist) addables.get(i)));
                    }
                }
            }
        }
        this.playlist = playlist;
        setVisible(false);
        setVisible(true);
    }

    public void setAddables(ArrayList<Addable> addables) {
        this.addables = addables;
        sortAddablesByLastPlayed();
        playlist = null;
    }

    public void setAddablesToAllSongs() {
        int size = addables.size();
        for (int i = 0; i < size; i++)
            addables.remove(0);
        for (int i = 0; i < Manager.getInstance().getSongs().size(); i++) {
            addables.add(Manager.getInstance().getSongs().get(i));
        }
        sortAddablesByLastPlayed();
        playlist = null;
    }

    public void setAddablesToAllPlaylists() {
        int size = addables.size();
        for (int i = 0; i < size; i++)
            addables.remove(0);
        for (int i = 0; i < Manager.getInstance().getPlaylists().size(); i++) {
            addables.add(Manager.getInstance().getPlaylists().get(i));
        }
        sortAddablesByLastPlayed();
        playlist = null;
    }

    public void setAddablesToAllAlbums() {
        int size = addables.size();
        for (int i = 0; i < size; i++)
            addables.remove(0);
        for (int i = 0; i < Manager.getInstance().getAlbums().size(); i++) {
            addables.add(Manager.getInstance().getAlbums().get(i));
        }
        sortAddablesByLastPlayed();
        playlist = null;
    }

    public void setAddables(SongCollection songCollection) {
        int size = addables.size();
        for (int i = 0; i < size; i++)
            addables.remove(0);
        for (int i = 0; i < songCollection.getSongs().size(); i++) {
            addables.add(songCollection.getSongs().get(i));
        }
        sortAddablesByLastPlayed();
        playlist = null;
    }

    public ArrayList<Addable> getAddables() {
        return addables;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void sortAddablesByLastPlayed() {
        if (addables.size() >= 1) {
            if (addables.get(0) instanceof Song) {
                for (int i = 0; i < addables.size() - 1; i++) {
                    for (int j = i + 1; j < addables.size(); j++) {
                        if (((Song)addables.get(i)).getLastTimePlayed() < ((Song)addables.get(j)).getLastTimePlayed()) {
                            Addable tmp = addables.get(i);
                            addables.add(i, addables.get(j));
                            addables.remove(i + 1);
                            addables.add(j, tmp);
                            addables.remove(j + 1);
                        }
                    }
                }
            }
        }
    }
}
