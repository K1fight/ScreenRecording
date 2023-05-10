package org.receving;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class Client {
    Get get;
    Frame frame;
    FrameRecorder recorder;
    String outputFile;
    int captureWidth,captureHeight;
    public Client() throws IOException {
        get = new Get();
        captureHeight = 900;
        captureWidth = 1440;
        recording();
    }
    public void start() throws IOException, ClassNotFoundException {
        System.out.println("start");
        for(int i = 0;i<300;i++){
            while (get.getEmpty());
            frame = get.get();
            recorder.record(frame);
        }
        get.close();
        recorder.stop();
        recorder.release();
        System.out.println("Finish");
    }
    private void recording() throws FrameRecorder.Exception {
        outputFile = "./" + LocalTime.now() + ".mp4";
        recorder = new FFmpegFrameRecorder(outputFile,captureWidth,captureHeight,2);
        recorder.setInterleaved(true);
        recorder.setVideoQuality(0);
        recorder.setVideoOption("crf","18");
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
        recorder.setFormat("mp4");
        recorder.setSampleRate(44100);
        recorder.setFrameRate(60);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setVideoBitrate(10000*1000);
        recorder.setGopSize(60);
        recorder.setVideoOption("preset","ultrafast");
        recorder.start();

    }
}
