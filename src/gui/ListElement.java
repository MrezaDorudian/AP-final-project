package gui;

import logic.Album;
import logic.Manager;
import logic.Playlist;
import logic.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ListElement extends Element{
    private final Font FONT1 = new Font("Microsoft Sans Serif", Font.BOLD, 12);
    private final Font FONT2 = new Font("Microsoft Sans Serif", Font.PLAIN, 10);
    private final Font FONT3 = new Font("Microsoft Sans Serif", Font.BOLD, 14);
    protected final int PANEL_WIDTH;
    protected final int PANEL_HEIGHT;

    public ListElement() {
        PANEL_WIDTH = 5000;
        PANEL_HEIGHT = 90;
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        add(button);
        button.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(50, 50, 50), new Color(50, 50, 50)));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                button.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                button.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(36, 174, 239), new Color(36, 174, 239)));

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(36, 174, 239), new Color(36, 174, 239)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(50, 50, 50), new Color(50, 50, 50)));
            }
        });
        SwingUsefulMethods.JButtonSetIcon(this, addToPlaylist, "ICON_SOURCE\\plus10i.png", 10, 10);
        addToPlaylist.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(50, 50, 50), new Color(50, 50, 50)));
        addToPlaylist.setBorderPainted(false);
        addToPlaylist.setFocusPainted(false);
        addToPlaylist.setBackground(new Color(40, 40, 40));
        addToPlaylist.setBounds(360, 5, 10, 10);
        addToPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                SwingUsefulMethods.JButtonSetIcon(ListElement.this, addToPlaylist, "ICON_SOURCE\\plus10ib.png", 10, 10);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                SwingUsefulMethods.JButtonSetIcon(ListElement.this, addToPlaylist, "ICON_SOURCE\\plus10i.png", 10, 10);
            }
        });
        this.add(addToPlaylist);
        button.setBounds(0, 0, PANEL_HEIGHT, PANEL_HEIGHT);
        setBackground(new Color(40, 40, 40));
    }

    public ListElement(Playlist playlist) {
        this();
        BufferedImage img = null;
        if (playlist.getImage() == null) {
            try {
                img = ImageIO.read(getClass().getResource("ICON_SOURCE\\default90.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            img = playlist.getImage();
        BufferedImage finalImg = new BufferedImage(PANEL_HEIGHT, PANEL_HEIGHT, img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img, 0, 0, PANEL_HEIGHT, PANEL_HEIGHT, null);
        graphics2D.dispose();
        button.setIcon(new ImageIcon(finalImg));
        firstLabel = new JLabel(playlist.getName());
        add(firstLabel);
        firstLabel.setForeground(Color.WHITE);
        addToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlaylistChooser(playlist);
            }
        });
        firstLabel.setFont(FONT1);
        firstLabel.setBounds(110, 0, 250, 35);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddables(playlist);
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(playlist);
            }
        });
    }

    public ListElement(Album album) {
        this();
        BufferedImage img = null;
        if (album.getImage() == null) {
            try {
                img = ImageIO.read(getClass().getResource("ICON_SOURCE\\default90.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            img = album.getImage();
        BufferedImage finalImg = new BufferedImage(PANEL_HEIGHT, PANEL_HEIGHT, img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img, 0, 0, PANEL_HEIGHT, PANEL_HEIGHT, null);
        graphics2D.dispose();
        button.setIcon(new ImageIcon(finalImg));
        firstLabel = new JLabel(album.getName());
        add(firstLabel);
        firstLabel.setFont(FONT1);
        firstLabel.setBounds(110, 0, 250, 35);
        firstLabel.setForeground(Color.WHITE);
        secondLabel = new JLabel(album.getArtistName());
        add(secondLabel);
        secondLabel.setForeground(Color.LIGHT_GRAY);
        addToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlaylistChooser(album);
            }
        });
        secondLabel.setFont(FONT2);
        secondLabel.setBounds(110, 23, 250, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddables(album);
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(null);
            }
        });
    }

    public ListElement(Song song) {
        this();
        BufferedImage img = null;
        if (song.getImage() == null) {
            try {
                img = ImageIO.read(getClass().getResource("ICON_SOURCE\\default90.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            img = song.getImage();
        BufferedImage finalImg = new BufferedImage(PANEL_HEIGHT, PANEL_HEIGHT, img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img, 0, 0, PANEL_HEIGHT, PANEL_HEIGHT, null);
        graphics2D.dispose();
        button.setIcon(new ImageIcon(finalImg));
        firstLabel = new JLabel(song.getSongName());
        add(firstLabel);
        firstLabel.setFont(FONT1);
        firstLabel.setBounds(110, 0, 250, 35);
        firstLabel.setForeground(Color.WHITE);
        secondLabel = new JLabel(song.getAlbumName());
        add(secondLabel);
        secondLabel.setBounds(110, 23, 250, 25);
        secondLabel.setForeground(Color.LIGHT_GRAY);
        secondLabel.setFont(FONT2);
        thirdLabel = new JLabel(song.getArtistName());
        add(thirdLabel);
        thirdLabel.setFont(FONT2);
        thirdLabel.setBounds(110, 60, 250, 25);
        thirdLabel.setForeground(Color.LIGHT_GRAY);
        addToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlaylistChooser(song);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().setNowPlayingSong(song);
                Manager.getInstance().getMainFrame().getSouth().startSong();
                Manager.getInstance().updateNowPlaying();
            }
        });
    }

    public void setPlaylist(Song song, Playlist playlist) {
        if (removeFromPlaylist == null) {
            removeFromPlaylist = new JButton();
            SwingUsefulMethods.JButtonSetIcon(this, removeFromPlaylist, "ICON_SOURCE\\deletei.png", 10, 10);
            removeFromPlaylist.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(50, 50, 50), new Color(50, 50, 50)));
            removeFromPlaylist.setBorderPainted(false);
            removeFromPlaylist.setFocusPainted(false);
            removeFromPlaylist.setBackground(new Color(40, 40, 40));
            removeFromPlaylist.setBounds(380, 5, 10, 10);
            removeFromPlaylist.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    SwingUsefulMethods.JButtonSetIcon(ListElement.this, removeFromPlaylist, "ICON_SOURCE\\deleteib.png", 10, 10);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    SwingUsefulMethods.JButtonSetIcon(ListElement.this, removeFromPlaylist, "ICON_SOURCE\\deletei.png", 10, 10);
                }
            });
            this.add(removeFromPlaylist);
        } else {
            removeFromPlaylist.removeActionListener(removeFromPlaylist.getActionListeners()[0]);
        }
        removeFromPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the song from " + playlist.getName() + " playlist?");
                if (ans == JOptionPane.YES_OPTION) {
                    boolean isRemoved = playlist.removeSong(song);
                    if (isRemoved) {
                        Manager.getInstance().getMainFrame().getCenterCenterCenter().getAddables().remove(song);
                        Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(playlist);
                        JOptionPane.showMessageDialog(null, "The song has been removed successfuly!");
                    } else
                        JOptionPane.showMessageDialog(null, "Removing unsuccessful");
                }
            }
        });
    }
}
