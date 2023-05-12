package org.receving;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage buffer;
    public ImagePanel() throws IOException {
        this.buffer = ImageIO.read(new File("./2.bmp"));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(buffer,0,0,buffer.getWidth(),buffer.getHeight(),this);
    }
    public void setBuffer(BufferedImage img){
        buffer = img;
        repaint();
    }
}
