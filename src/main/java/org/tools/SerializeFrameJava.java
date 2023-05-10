package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SerializeFrameJava extends Thread{
    ByteArrayOutputStream baos;
    Java2DFrameConverter converter;
    BufferedImage buffer;
    Frame frame;
    byte[] data;
    public SerializeFrameJava(Frame frame) {
        converter = new Java2DFrameConverter();
        this.frame = frame;
    }
    public void serialize(Frame frame) throws IOException {
        System.out.println("Width:"+ frame.imageWidth);
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"png",baos);
        data = baos.toByteArray();
    }
    public byte[] getData(){
        return data;
    }
    @Override
    public void run() {
        try {
            serialize(frame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
