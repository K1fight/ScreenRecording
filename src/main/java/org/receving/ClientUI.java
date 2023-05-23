package org.receving;


import org.transmission.GetBuffer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    GetBuffer gb;
    private ClientUI() throws IOException, ClassNotFoundException, InterruptedException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLayout(new FlowLayout());

        p1 = new ImagePanel();
        p1.setLayout(new FlowLayout());
        p1.setPreferredSize(new Dimension(1280,720));
        p1.setVisible(true);

        gb = GetBuffer.getInstance();
        add(p1);
        pack();
        setVisible(true);
        display();

    }
    public void display() throws IOException, InterruptedException {
        gb.startMutithreads();

        for(int i = 0;i<600;i++){
            p1.setBuffer(gb.getData());
        }
        gb.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ClientUI.getInstance();
    }
}
