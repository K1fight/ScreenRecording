package org.recording;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
                } catch (AWTException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        p1.add(start);
        add(p1);
        setVisible(true);
    }
    private void startRecording() throws AWTException, IOException {
        JavacvRecording javaCV = new JavacvRecording();
        javaCV.start();

    }
    public static void main(String[]args){
        UI ui = UI.getInstance();
    }

}
