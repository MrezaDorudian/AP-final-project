package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingUsefulMethods {
    public static void JButtonSetIcon(Component component, JButton button, String iconPath, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(component.getClass().getResource(iconPath));
            BufferedImage finalImg = new BufferedImage(width, height, img.getType());
            Graphics2D graphics2D = finalImg.createGraphics();
            graphics2D.drawImage(img, 0, 0, width, height, null);
            graphics2D.dispose();
            button.setIcon(new ImageIcon(finalImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void paintScrollPane(JScrollPane scrollPane, Color barColor, Color backgroundColor) {
        scrollPane.getVerticalScrollBar().setBackground(backgroundColor);

        scrollPane.getVerticalScrollBar().setUI(new NoArrowScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = barColor;
            }
        });
    }
}
