package gui;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlaylistChooser extends JFrame {
    private static boolean isOpen = false;
    private JPanel panel;
    private final int ELEMENT_WIDTH;
    private final int ELEMENT_HEIGHT;
    private final Font FONT2 = new Font("Microsoft Sans Serif", Font.PLAIN, 11);
    private final Color MY_GRAY = new Color(30, 30, 30);

    public PlaylistChooser(Addable addable) {
        super("Choose a Playlist");
        ELEMENT_WIDTH = 5000;
        ELEMENT_HEIGHT = 30;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                isOpen = false;
            }
        });
        if (!isOpen) {
            setSize(250, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            setLayout(new BorderLayout());
            isOpen = true;

            panel = new JPanel();
            panel.setLayout(new ModifiedFlowLayout());
            add(new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
            panel.setBackground(MY_GRAY);
            for (Playlist playlist : Manager.getInstance().getPlaylists()) {
                addElement(playlist, addable);
            }
            setVisible(true);
            setResizable(false);
        }
    }

    public void addElement(Playlist playlist, Addable addable) {
        JButton button = new JButton(playlist.getName());
        button.setBorderPainted(false);
        button.setBackground(MY_GRAY);
        button.setFocusPainted(false);
        button.setFont(FONT2);
        button.setForeground(Color.WHITE);
        panel.add(button, ModifiedFlowLayout.LEFT);
        SwingUsefulMethods.JButtonSetIcon(this, button, "ICON_SOURCE\\playlisti.png", 15, 15);
        button.setPreferredSize(new Dimension(ELEMENT_WIDTH, ELEMENT_HEIGHT));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                SwingUsefulMethods.JButtonSetIcon(PlaylistChooser.this, button, "ICON_SOURCE\\playlistib.png", 15, 15);
                button.setBackground(new Color(43, 43, 43));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                SwingUsefulMethods.JButtonSetIcon(PlaylistChooser.this, button, "ICON_SOURCE\\playlisti.png", 15, 15);
                button.setBackground(MY_GRAY);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addable instanceof Song) {
                    playlist.addSong((Song) addable);
                } else if (addable instanceof SongCollection) {
                    playlist.addSongs((SongCollection) addable);
                }
                PlaylistChooser.this.dispose();
                isOpen = false;
            }
        });
    }
}
