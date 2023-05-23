package org.recording;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.transmission.Post;
import org.transmission.PostBuffer;


import java.awt.*;
import java.io.IOException;


public class JavacvRecording {
    private static JavacvRecording recording;
    PostBuffer pb;
    double captureWidth,captureHeight;
    int x,y;
    String option,format,device,osName;
    Rectangle screenSize;
    FFmpegFrameGrabber grabber;
    Frame frame;
    boolean status;

    public static JavacvRecording getInstance() throws IOException, ClassNotFoundException {
        if(recording == null){
            recording = new JavacvRecording();
            return  recording;
        }
        return recording;
    }


    private JavacvRecording() throws IOException, ClassNotFoundException {
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        captureWidth = screenSize.getWidth();
        captureHeight = screenSize.getHeight();
        x = 0;
        y = 0;
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
        pb = PostBuffer.getInstance();
        status = true;
    }
    public void start() throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("Start");
        pb.startMutiThread();
        while(status){
            frame = grabber.grab();
            pb.setData(frame);
        }
    }
    public void stop(){
        status = false;
    }
    public void exit() throws IOException, InterruptedException {
        grabber.stop();
        grabber.release();
        pb.close();
        System.out.println("Finish");
    }
    private void capture() throws FFmpegFrameGrabber.Exception {
        grabber = new FFmpegFrameGrabber(device);
        grabber.setOption(option,"1");
        grabber.setFormat(format);
        grabber.setPixelFormat(avutil.AV_PIX_FMT_NV12);
        grabber.setImageHeight((int)captureHeight);
        grabber.setImageWidth((int)captureWidth);
        grabber.setFrameRate(60);
        grabber.start();
    }
    public static void closeRecording(){
        recording = null;
    }

}
