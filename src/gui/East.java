package gui;

import network.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class East extends JPanel {

    private final Font FONT1 = new Font("Microsoft Sans Serif", Font.BOLD, 11);
    private final Font FONT4 = new Font("Microsoft Sans Serif", Font.PLAIN, 11);
    private final Font FONT2 = new Font("Microsoft Sans Serif", Font.PLAIN, 10);
    private final Font FONT3 = new Font("Microsoft Sans Serif", Font.PLAIN, 9);
    private final Color MY_GRAY = new Color(30, 30, 30);
    private final int ELEMENT_WIDTH;
    private final int ELEMENT_HEIGHT;
    private ArrayList<Component> myComponents = new ArrayList<>();

    public East() {
        ELEMENT_WIDTH = 145;
        ELEMENT_HEIGHT = 25;
        setLayout(new BorderLayout());
        this.setLayout(new ModifiedFlowLayout());
        setBackground(MY_GRAY);
        JLabel friendActivityLabel = makeLabel("Friend Activity");
        friendActivityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        friendActivityLabel.setFont(FONT1);
        friendActivityLabel.setPreferredSize(new Dimension(ELEMENT_WIDTH, 13));
        add(friendActivityLabel, 0);
        setBounds(0, 0, 130, 0);
    }

    public JButton makeButton(String text, String imagePath, int alignment) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setBackground(MY_GRAY);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        if (imagePath != null) {
            SwingUsefulMethods.JButtonSetIcon(this, button, imagePath + ".png", 10, 10);
            button.setFont(FONT2);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    SwingUsefulMethods.JButtonSetIcon(East.this, button, imagePath + "b.png", 10, 10);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    SwingUsefulMethods.JButtonSetIcon(East.this, button, imagePath + ".png", 10, 10);
                }
            });
        }
        button.setPreferredSize(new Dimension(ELEMENT_WIDTH, ELEMENT_HEIGHT));
        button.setHorizontalAlignment(alignment);
        return button;
    }

    public JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FONT4);
        label.setBackground(MY_GRAY);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(ELEMENT_WIDTH, 20));
        return label;
    }

    public void addUser(Message message) {
        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY));
        panel.setBounds(0, 0, 130, 1);
        panel.setBackground(MY_GRAY);
        panel.setLayout(new BorderLayout());
        JButton button = makeButton(message.getFriendID(), "ICON_SOURCE\\speakeri", SwingConstants.LEFT);
        panel.add(button, BorderLayout.NORTH);

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(MY_GRAY);
        labelPanel.setLayout(null);
        labelPanel.setPreferredSize(new Dimension(ELEMENT_WIDTH, 60));
        panel.add(labelPanel, BorderLayout.CENTER);

        JLabel songNameLabel = makeLabel(message.getSongName());
        JLabel artistNameLabel = makeLabel(message.getArtistName());
        JLabel timeLabel;
        if (message.getOfflineTime() != 0)
            timeLabel = makeLabel(message.getOfflineTime() + " ago");
        else
            timeLabel = makeLabel("Now");

        labelPanel.add(songNameLabel);
        labelPanel.add(artistNameLabel);
        labelPanel.add(timeLabel);
        songNameLabel.setFont(FONT3);
        artistNameLabel.setFont(FONT3);
        timeLabel.setFont(FONT3);
        songNameLabel.setBounds(30, 0, ELEMENT_WIDTH, 15);
        artistNameLabel.setBounds(30, 13, ELEMENT_WIDTH, 15);
        timeLabel.setBounds(30, 40, ELEMENT_WIDTH, 10);
        this.add(panel, 1);
    }

    public ArrayList<Component> getMyComponents() {
        return myComponents;
    }
}