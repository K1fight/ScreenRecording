package org.recording;

import javafx.geometry.Pos;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;


import java.awt.*;
import java.io.IOException;


public class JavacvRecording {
    Post post;
    double captureWidth,captureHeight;
    int x,y;
    String option,format,device,osName;
    Rectangle screenSize;
    FFmpegFrameGrabber grabber;
    Frame frame;
    Frame[] bufferFrame;
    SerializeFrameJava ser;
    MyThreadspool pool;


    public JavacvRecording() throws IOException, ClassNotFoundException {
        pool = new MyThreadspool();
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        captureWidth = screenSize.getWidth();
        captureHeight = screenSize.getHeight();
        x = 0;
        y = 0;
        bufferFrame = new Frame[12];
        osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            format = "gdigrab";
            option = "desktop";
            device = "desktop";
        } else if (osName.startsWith("mac")) {
            format = "avfoundation";
            option = "capture_cursor";
            device = "1";
        } else { // Linux
            format = "x11grab";
            option = "draw_mouse";
            device = ":0.0";
        }
        capture();
        post = Post.getInstance();
    }
    public void start() throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("Start");
        long start = System.nanoTime();
        for(int i =0 ;i<600;i++){
            frame = grabber.grab();
            post.start(frame);
        }
        long end = System.nanoTime();
        System.out.println(300/((end-start)/1_000_000_000));
        grabber.stop();
        grabber.release();
        post.close();
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
