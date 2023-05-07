package org.recording;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;


public class JavacvRecording {
    double captureWidth,captureHeight;
    int x,y;
    String option,format,device,outputFile,unsharp;
    Rectangle screenSize;
    FFmpegFrameGrabber grabber;
    FrameRecorder recorder;

    public JavacvRecording(){
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        captureWidth = screenSize.getWidth();
        captureHeight = screenSize.getHeight();
        x = 0;
        y = 0;
        format = "avfoundation";
        option = "capture_cursor";
        device = "1";
        unsharp =  "unsharp=5:5:1.0:5:5:0.0";
    }
    public void start() throws IOException {
        System.out.println("Start");
        capture();
        recording();
        Frame frame;
        for(int i =0 ;i<120;i++){
            frame = grabber.grab();
            recorder.record(frame);
        }
//        frame = grabber.grab();
//        Java2DFrameConverter converter  = new Java2DFrameConverter();
//        BufferedImage image = converter.convert(filtered);
//        ImageIO.write(image,"bmp",new File("./2.bmp"));
        recorder.stop();
        recorder.release();
        grabber.stop();
        grabber.release();
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
    private void recording() throws FrameRecorder.Exception {
        outputFile = "./" + LocalTime.now() + ".mp4";
        recorder = new FFmpegFrameRecorder(outputFile,(int)captureWidth,(int)captureHeight,2);
        recorder.setInterleaved(true);
        recorder.setVideoOption("crf","18");
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");
        recorder.setFrameRate(60);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setVideoBitrate(10000*1000);
        recorder.setGopSize(60);
        recorder.setVideoOption("preset","veryfast");
        recorder.start();

    }

}
