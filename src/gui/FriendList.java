package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FriendList extends JFrame {
    private static boolean isOpen = false;
    private JPanel panel;
    private final int ELEMENT_WIDTH;
    private final int ELEMENT_HEIGHT;
    private final Font FONT1 = new Font("Microsoft Sans Serif", Font.BOLD, 10);
    private final Color MY_GRAY = new Color(30, 30, 30);

    public FriendList() {
        super("Friend List");
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
            setResizable(false);
            setVisible(true);

            addElement("Parsa");
            addElement("Mamrez");
            addElement("Mahsa");
            addElement("Zahra");
        }
    }

    public void addElement(String friendName) {
        JButton button = new JButton(friendName);
        button.setBorderPainted(false);
        button.setBackground(MY_GRAY);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        panel.add(button, ModifiedFlowLayout.LEFT);
        SwingUsefulMethods.JButtonSetIcon(this, button, "ICON_SOURCE\\i.png", 10, 10);
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
                SwingUsefulMethods.JButtonSetIcon(FriendList.this, button, "ICON_SOURCE\\ib.png", 10, 10);
                button.setBackground(new Color(43, 43, 43));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                SwingUsefulMethods.JButtonSetIcon(FriendList.this, button, "ICON_SOURCE\\i.png", 10, 10);
                button.setBackground(MY_GRAY);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FriendList.this.dispose();
                isOpen = false;
            }
        });
    }
}
