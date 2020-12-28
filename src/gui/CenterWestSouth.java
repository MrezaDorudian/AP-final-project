package gui;

import logic.Manager;
import logic.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CenterWestSouth extends JPanel {
    private final Color MY_GRAY = new Color(30, 30, 30);
    private JLabel label = new JLabel();

    public CenterWestSouth() throws IOException {
        setLayout(new FlowLayout());
        changeImage();
        label.setBackground(MY_GRAY);
        setBackground(MY_GRAY);
        label.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY));
        add(label);
    }

    public void changeImage() throws IOException {
        BufferedImage img;
        if (Manager.getInstance().getNowPlayingSong() == null || (Manager.getInstance().getNowPlayingSong() != null && Manager.getInstance().getNowPlayingSong().getImage() == null))
            img = ImageIO.read(getClass().getResource("ICON_SOURCE\\default130.jpg"));
        else
            img = Manager.getInstance().getNowPlayingSong().getImage();
        try {
            BufferedImage finalImg = new BufferedImage(130, 130, img.getType());
            Graphics2D graphics2D = finalImg.createGraphics();
            graphics2D.drawImage(img, 0, 0, 130, 130, null);
            graphics2D.dispose();
            label.setIcon(new ImageIcon(finalImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
