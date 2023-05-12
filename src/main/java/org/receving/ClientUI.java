package org.receving;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientUI extends JFrame {
    private static ClientUI ui;
    public static ClientUI getInstance() throws IOException, ClassNotFoundException, InterruptedException {
        if(ui==null){
            ui = new ClientUI();
            return ui;
        }
        return ui;
    }
    ImagePanel p1;
    Get get;
    public ClientUI() throws IOException, ClassNotFoundException, InterruptedException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLayout(new FlowLayout());

        p1 = new ImagePanel();
        p1.setLayout(new FlowLayout());
        p1.setPreferredSize(new Dimension(1280,720));
        p1.setVisible(true);


        get = Get.getInstance();
        add(p1);
        pack();
        setVisible(true);
        display();

    }
    public void display() throws IOException, ClassNotFoundException, InterruptedException {

        get.receiveData();
        get.start();
        for(int i = 0;i<600;i++){
            BufferedImage buffer = get.getFirst();
            p1.setBuffer(buffer);
        }
        get.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ClientUI.getInstance();
    }
}
