package org.recording;


import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    JButton start ;
    JPanel p1;
    public static UI ui;
    public static UI getInstance(){
        if(ui==null){
            return new UI();
        }
        return ui;
    }
    private UI(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,100);
        setLayout(new FlowLayout());
        p1 = new JPanel();
        p1.setSize(200,100);
        p1.setLayout(new FlowLayout());
        start = new JButton("start");
        start.setSize(30,20);
        start.addActionListener(e -> {
            if(e.getSource()==start){
                try {
                    startRecording();
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        p1.add(start);
        add(p1);
        setVisible(true);
    }
    private void startRecording() throws AWTException {
        ScreenRecording screenRecording = new ScreenRecording();
        screenRecording.start();

    }
    public static void main(String[]args){
        UI ui = UI.getInstance();
    }

}
