package gui;

import logic.Addable;
import logic.Manager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CenterCenterNorth extends JPanel {
    private JButton button;
    private JTextField searchBar;
    private JPanel searchPanel;
    private JButton searchButton;
    private final Font FONT1 = new Font("Microsoft Sans Serif", Font.PLAIN, 12);
    private final Color MY_GRAY = new Color(30, 30, 30);

    public CenterCenterNorth() {
        setLayout(new BorderLayout());
        setBackground(MY_GRAY);
        button = new JButton(Manager.getInstance().getMainFrame().getUsername());
        add(button, BorderLayout.EAST);
        button.setFont(FONT1);
        button.setBorderPainted(false);
        button.setBackground(MY_GRAY);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        SwingUsefulMethods.JButtonSetIcon(this, button, "ICON_SOURCE\\i.png", 10, 10);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FriendList();
            }
        });
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUsefulMethods.JButtonSetIcon(CenterCenterNorth.this, button, "ICON_SOURCE\\ib.png", 10, 10);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUsefulMethods.JButtonSetIcon(CenterCenterNorth.this, button, "ICON_SOURCE\\i.png", 10, 10);
            }
        });
        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(150, 15));
        searchBar.setBackground(MY_GRAY);
        searchBar.setFont(FONT1);
        searchBar.setForeground(Color.WHITE);
        searchBar.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(43, 43, 43), MY_GRAY));
        new GhostText(searchBar, "Search for a Music...");

        searchButton = new JButton();
        searchButton.setBorderPainted(false);
        searchButton.setBackground(MY_GRAY);
        searchButton.setFocusPainted(false);
        searchButton.setForeground(Color.WHITE);
        SwingUsefulMethods.JButtonSetIcon(this, searchButton, "ICON_SOURCE\\search4.png", 15, 15);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUsefulMethods.JButtonSetIcon(CenterCenterNorth.this, searchButton, "ICON_SOURCE\\search4b.png", 15, 15);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUsefulMethods.JButtonSetIcon(CenterCenterNorth.this, searchButton, "ICON_SOURCE\\search4.png", 15, 15);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.getInstance().getMainFrame().getCenterCenterCenter().refreshSearchResults(Manager.getInstance().getMainFrame().getCenterCenterCenter().getPlaylist(), searchBar.getText());
            }
        });
        searchPanel = new JPanel();
        searchPanel.setBackground(MY_GRAY);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.WEST);
        add(searchPanel, BorderLayout.WEST);
    }
}
