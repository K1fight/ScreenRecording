package org.receving;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage buffer;
    public ImagePanel(){
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
