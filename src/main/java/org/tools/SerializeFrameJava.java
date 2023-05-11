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
    public byte[] serialize(Frame frame) throws IOException {
        System.out.println(this.frame.imageWidth);
        System.out.println("1");
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"png",baos);
        data = baos.toByteArray();
        return data;
    }
    public byte[] getData(){
        return this.data;
    }
    @Override
    public void run() {
        try {
            serialize(this.frame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
