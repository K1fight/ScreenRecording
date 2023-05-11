package org.receving;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame {
    private static ClientUI ui;
    public static ClientUI getInstance(){
        if(ui==null){
            ui = new ClientUI();
            return ui;
        }
        return ui;
    }
    JPanel p1;
    public ClientUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,720);
        setLayout(new FlowLayout());

        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setSize(1290,720);
        p1.setVisible(true);



        display();
        pack();
        setVisible(true);
    }
    public void display(){
        add(p1);
    }
    public static void main(String[] args){
        ClientUI.getInstance();
    }
}
