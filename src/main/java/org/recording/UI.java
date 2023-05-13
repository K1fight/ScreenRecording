package org.recording;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class UI extends JFrame {
    Thread t1,t2,t3;
    JButton start ,stop,exit;
    JPanel p1;
    JavacvRecording javaCV;
    public static UI ui;
    public static UI getInstance() throws IOException, ClassNotFoundException {
        if(ui==null){
            return new UI();
        }
        return ui;
    }
    private UI() throws IOException, ClassNotFoundException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,100);
        setLayout(new FlowLayout());
        p1 = new JPanel();
        p1.setSize(200,100);
        p1.setLayout(new FlowLayout());
        start = new JButton("start");
        stop = new JButton("stop");
        exit = new JButton("exit");
        exit.setSize(30,20);
        stop.setSize(30,20);
        start.setSize(30,20);
        start.addActionListener(e -> {
            if(e.getSource()==start){
                t1 = new Thread("t1"){
                    @Override
                    public void run(){
                        try {
                            javaCV = JavacvRecording.getInstance();
                            javaCV.start();
                        } catch (IOException | ClassNotFoundException | InterruptedException exc) {
                            throw new RuntimeException(exc);
                        }
                    }
                };
                t1.start();
            }
        });
        stop.addActionListener(e->{
            if(e.getSource()==stop){
                t2 = new Thread("t2"){
                    @Override
                    public void run(){
                        javaCV.stop();
                        JavacvRecording.closeRecording();
                    }
                };
                t2.start();
            }
        });
        exit.addActionListener(e->{
            if(e.getSource()==exit){
                t3 = new Thread("t3"){
                    @Override
                    public void run(){
                        try {
                            javaCV.exit();
                        } catch (IOException | InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                };
                t3.start();
            }
        });

        p1.add(start);
        p1.add(stop);
        p1.add(exit);
        add(p1);
        pack();
        setVisible(true);
    }
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        UI.getInstance();
    }
}

