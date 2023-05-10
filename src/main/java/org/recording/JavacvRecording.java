package org.recording;

import javafx.geometry.Pos;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;


import java.awt.*;
import java.io.IOException;


public class JavacvRecording {
    Post post;
    double captureWidth,captureHeight;
    int x,y;
    String option,format,device,outputFile;
    Rectangle screenSize;
    FFmpegFrameGrabber grabber;
    Frame frame;

    public JavacvRecording() throws IOException, ClassNotFoundException {
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        captureWidth = screenSize.getWidth();
        captureHeight = screenSize.getHeight();
        x = 0;
        y = 0;
        format = "avfoundation";
        option = "capture_cursor";
        device = "1";
        capture();
        post = new Post();
    }
    public void start() throws IOException {
        System.out.println("Start");
        long start = System.nanoTime();
        for(int i =0 ;i<300;i++){
            frame = grabber.grab();
            post.start(frame);
        }
        long end = System.nanoTime();
        System.out.println(300/((end-start)/1_000_000_000));
        grabber.stop();
        grabber.release();
//        post.close();
        System.out.println("Finish");


    }
    private void capture() throws FFmpegFrameGrabber.Exception {
        grabber = new FFmpegFrameGrabber(device);
        grabber.setOption(option,"1");
        grabber.setFormat(format);
        grabber.setImageHeight((int)captureHeight);
        grabber.setImageWidth((int)captureWidth);
        grabber.setFrameRate(60);
        grabber.start();

    }

}
