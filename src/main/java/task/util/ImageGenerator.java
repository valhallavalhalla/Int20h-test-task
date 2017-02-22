package task.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenerator {

    public static File imageFromText(String text) {
        String [] lines = text.split("\n");

        BufferedImage img = new BufferedImage(850, 820, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(font);
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        int lineHeight = g2d.getFontMetrics().getHeight();
        for(int lineCount = 0; lineCount < lines.length; lineCount ++){ //lines from above
            int xPos = 40;
            int yPos = 40 + lineCount * lineHeight;
            String line = lines[lineCount];
            g2d.drawString(line, xPos, yPos);
        }

        File file = new File("programs/program.png");

        g2d.dispose();
        try {
            ImageIO.write(img, "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return file;
    }

}
