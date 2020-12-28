package gui;

import logic.Library;
import logic.Manager;
import logic.Playlist;
import logic.Song;

import javax.imageio.ImageIO;
import javax.management.MalformedObjectNameException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CenterWestCenter extends JPanel {

    private JButton allPlaylists;
    private JButton allSongs;
    private JButton allAlbums;
    private JButton newLibrary;
    private JButton newPlaylist;
    private JLabel allLibraries;
    private JButton favorite;
    private JButton shared;

    private final Font FONT1 = new Font("Microsoft Sans Serif", Font.BOLD, 10);
    private final Font FONT2 = new Font("Microsoft Sans Serif", Font.PLAIN, 11);
    private final Color MY_GRAY = new Color(30, 30, 30);
    private final int ELEMENT_WIDTH;
    private final int ELEMENT_HEIGHT;

    public CenterWestCenter() {
        ELEMENT_WIDTH = 145;
        ELEMENT_HEIGHT = 25;
        this.setLayout(new ModifiedFlowLayout());
        setBackground(MY_GRAY);
        allLibraries = makeLabel("All Libraries", SwingConstants.CENTER);
        newLibrary = makeButton("New Library", "ICON_SOURCE\\plusi", 8, SwingConstants.CENTER, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        newLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mp3", "mp3", "mp3");
                fileChooser.setFileFilter(filter);
                fileChooser.setDialogTitle("Choose a Path");
                int result = fileChooser.showSaveDialog(newLibrary);
                File file = fileChooser.getSelectedFile();
                while (result == JFileChooser.APPROVE_OPTION && file.isFile() && !file.getAbsolutePath().endsWith(".mp3")) {
                    JOptionPane.showMessageDialog(null, "The selected file should only have mp3 format");
                    result = fileChooser.showSaveDialog(newLibrary);
                    file = fileChooser.getSelectedFile();
                }
                if (result == JFileChooser.APPROVE_OPTION) {
                    Library library = new Library(file);
                    Manager.getInstance().addLibrary(library);
                }
            }
        });
        allSongs = makeButton("All Songs", null, 0, SwingConstants.CENTER, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        allSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddablesToAllSongs();
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(null);
            }
        });
        allPlaylists = makeButton("All Playlists", null, 0, SwingConstants.CENTER, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        allPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddablesToAllPlaylists();
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(null);
            }
        });
        newPlaylist = makeButton("New Playlist", "ICON_SOURCE\\plusi", 8, SwingConstants.CENTER, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        newPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewPlaylist newPlaylist = new NewPlaylist();
            }
        });
        allAlbums = makeButton("All Albums", null, 0, SwingConstants.CENTER, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        allAlbums.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddablesToAllAlbums();
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(null);
            }
        });
        Playlist favoritePlaylist = new Playlist("Favorite");
        Manager.getInstance().addPlaylist(favoritePlaylist);
        favorite = makeButton("Favorite", "ICON_SOURCE\\playlisti", 15, SwingConstants.LEFT, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        favorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddables(favoritePlaylist);
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(favoritePlaylist);
            }
        });
        Playlist sharedPlaylist = new Playlist("Shared");
        Manager.getInstance().addPlaylist(sharedPlaylist);
        shared = makeButton("Shared", "ICON_SOURCE\\playlisti", 15, SwingConstants.LEFT, ELEMENT_WIDTH, ELEMENT_HEIGHT, true);
        shared.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddables(sharedPlaylist);
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(sharedPlaylist);
            }
        });
        setBounds(0, 0, 130, 0);
        add(allLibraries, 0);
        add(newLibrary, 1);
        add(allSongs, 2);
        add(allAlbums, 3);
        add(allPlaylists, 4);
        add(favorite, 5);
        add(shared, 6);
        add(newPlaylist, 7);
    }

    private JButton makeButton(String text, String imagePath, int width, int alignment, int buttonWidth, int buttonHeight, boolean shouldFlash) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setBackground(MY_GRAY);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        if (imagePath != null) {
            SwingUsefulMethods.JButtonSetIcon(this, button, imagePath + ".png", width, width);
            button.setFont(FONT2);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    SwingUsefulMethods.JButtonSetIcon(CenterWestCenter.this, button, imagePath + "b.png", width, width);
                    if (shouldFlash)
                        button.setBackground(new Color(43, 43, 43));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    SwingUsefulMethods.JButtonSetIcon(CenterWestCenter.this, button, imagePath + ".png", width, width);
                    if (shouldFlash)
                        button.setBackground(MY_GRAY);
                }
            });
        } else {
            button.setFont(FONT1);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (shouldFlash)
                        button.setBackground(new Color(43, 43, 43));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (shouldFlash)
                        button.setBackground(MY_GRAY);
                }
            });
        }
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setHorizontalAlignment(alignment);
        return button;
    }

    private JLabel makeLabel(String text, int alignment) {
        JLabel label = new JLabel(text);
        label.setFont(FONT1);
        label.setBackground(MY_GRAY);
        label.setForeground(Color.GRAY);
        label.setPreferredSize(new Dimension(ELEMENT_WIDTH, ELEMENT_HEIGHT));
        label.setHorizontalAlignment(alignment);
        return label;
    }

    public void addLibrary(Library library) {
        JPanel panel = new JPanel();
        add(panel, Manager.getInstance().getLibraries().size());
        panel.setBackground(MY_GRAY);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(ELEMENT_WIDTH, ELEMENT_HEIGHT));
        JButton button = makeButton(library.getPath().substring(library.getPath().lastIndexOf("\\") + 1), "ICON_SOURCE\\libraryi", 15, SwingConstants.LEFT, ELEMENT_WIDTH - 30, ELEMENT_HEIGHT, true);
        panel.add(button, BorderLayout.WEST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Do you want to refresh the " + library.getPath().substring(library.getPath().lastIndexOf("\\") + 1) + " library?\nDoing so will refresh the time of last played for all the songs related to the library.");
                if (ans == JOptionPane.YES_OPTION) {
                    Manager.getInstance().removeLibrarySongs(library);
                    Manager.getInstance().importLibrarySongs(library);
                    int size = Manager.getInstance().getNowPlaying().size();
                    for (int i = 0; i < size; i++)
                        Manager.getInstance().getNowPlaying().remove(0);
                    JOptionPane.showMessageDialog(null, "Library successfully refreshed!");
                }
            }
        });
        JButton removeButton = makeButton("", "ICON_SOURCE\\removei", 15, SwingConstants.CENTER, ELEMENT_HEIGHT, ELEMENT_HEIGHT, false);
        panel.add(removeButton, BorderLayout.CENTER);
        JPanel empty = new JPanel();
        empty.setBackground(MY_GRAY);
        empty.setLayout(new BorderLayout());
        empty.setPreferredSize(new Dimension(15, ELEMENT_HEIGHT));
        panel.add(empty, BorderLayout.EAST);
        setVisible(false);
        setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(removeButton, "Are you sure you want to remove this library?");
                if (ans == JOptionPane.YES_OPTION) {
                    Manager.getInstance().removeLibrarySongs(library);
                    Manager.getInstance().removeLibrary(library);
                    remove(panel);
                    setVisible(false);
                    setVisible(true);
                }
            }
        });
    }

    public void addPlaylist(Playlist playlist) {
        JPanel panel = new JPanel();
        add(panel, 7 + Manager.getInstance().getLibraries().size());
        panel.setBackground(MY_GRAY);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(ELEMENT_WIDTH, ELEMENT_HEIGHT));
        JButton button = makeButton(playlist.getName(), "ICON_SOURCE\\playlisti", 15, SwingConstants.LEFT, ELEMENT_WIDTH - 55, ELEMENT_HEIGHT, true);
        panel.add(button, BorderLayout.WEST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().setAddables(playlist);
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(playlist);
            }
        });
        JButton removeButton = makeButton("", "ICON_SOURCE\\removei", 15, SwingConstants.CENTER, 20, ELEMENT_HEIGHT, false);
        JButton renameButton = makeButton("", "ICON_SOURCE\\renamei", 15, SwingConstants.CENTER, 20, ELEMENT_HEIGHT, false);
        JPanel center = new JPanel();
        panel.add(center, BorderLayout.CENTER);
        center.setBackground(MY_GRAY);
        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(50, ELEMENT_HEIGHT));
        center.add(removeButton, BorderLayout.EAST);
        center.add(renameButton, BorderLayout.CENTER);
        JPanel empty = new JPanel();
        empty.setBackground(MY_GRAY);
        empty.setLayout(new BorderLayout());
        empty.setPreferredSize(new Dimension(15, ELEMENT_HEIGHT));
        panel.add(empty, BorderLayout.EAST);
        setVisible(false);
        setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this playlist?");
                if (ans == JOptionPane.YES_OPTION) {
                    Manager.getInstance().removePlaylist(playlist);
                    Manager.getInstance().getMainFrame().getCenterCenterCenter().refresh(Manager.getInstance().getMainFrame().getCenterCenterCenter().getPlaylist());
                    remove(panel);
                    setVisible(false);
                    setVisible(true);
                }
            }
        });
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RenamePlaylist renamePlaylist = new RenamePlaylist(playlist, button);
            }
        });
    }
}
