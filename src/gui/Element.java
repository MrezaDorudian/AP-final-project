package gui;

import logic.Playlist;
import logic.Song;

import javax.swing.*;
import java.awt.*;

public abstract class Element extends JPanel{
    protected JButton button = new JButton();
    protected JLabel firstLabel;
    protected JLabel secondLabel;
    protected JLabel thirdLabel;
    protected JButton removeFromPlaylist;
    protected JButton addToPlaylist = new JButton();
    protected JButton shiftBack = new JButton();
    protected JButton shiftFront = new JButton();
    protected final Color MY_GRAY2 = new Color(50, 50, 50);

    public abstract void setPlaylist(Song song, Playlist playlist);
}
