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
import java.util.ArrayList;
import java.util.Timer;

public class ScreenRecording {

    static {OpenCV.loadLocally();}
    ArrayList<BufferedImage> buffer;

    String output;
    Robot robot;
    Rectangle screenSize;
    Size size;
    int fourcc;
    VideoWriter writer;
    BufferedImage image;

    public ScreenRecording() throws AWTException {
        buffer = new ArrayList<>();
        output = "./" + LocalTime.now() +".mp4";
        robot = new Robot();
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        size = new Size(screenSize.getWidth(),screenSize.getHeight());
        fourcc = VideoWriter.fourcc('H','2','6','4');
        writer = new VideoWriter(output,fourcc,10.68,size,true);
    }

    public  void start() {
        System.out.println("Start");
        int i = 0;
        long start = System.nanoTime();
        while(i<120){
            image = robot.createScreenCapture(screenSize);
            buffer.add(image);
            i++;
        }
        long end = System.nanoTime();
        double period = (end - start)/1_000_000_000.0;
        System.out.printf("%.2f%n",buffer.size()/period);

        for(BufferedImage image : buffer){
            Mat img = bufferedImageToMat(image);
            writer.write(img);
        }
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
