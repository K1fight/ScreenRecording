package org.recording;

import nu.pattern.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.time.LocalTime;

public class ScreenRecording {

    static {OpenCV.loadLocally();}

    String output;
    Robot robot;
    Rectangle screenSize;
    Size size;
    int fourcc;
    VideoWriter writer;

    public ScreenRecording() throws AWTException {
        output = "./" + LocalTime.now() +".mp4";
        robot = new Robot();
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        size = new Size(screenSize.getWidth(),screenSize.getHeight());
        fourcc = VideoWriter.fourcc('H','2','6','4');
        writer = new VideoWriter(output,fourcc,30.0,size,true);
    }

    public  void start() {
        System.out.println("Start");
        int i = 0;
        do {
            BufferedImage image = robot.createScreenCapture(screenSize);
            Mat img = bufferedImageToMat(image);
            writer.write(img);
            i++;
        } while (i != 120);
        writer.release();

    }
    public Mat bufferedImageToMat(BufferedImage bi) {
        BufferedImage img = convertTo3ByteBGRType(bi);
        Mat mat = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    public BufferedImage convertTo3ByteBGRType(BufferedImage image) {
        BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        convertedImage.getGraphics().drawImage(image, 0, 0, null);
        return convertedImage;
    }
}
