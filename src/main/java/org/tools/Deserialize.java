package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Deserialize implements Runnable{
    Java2DFrameConverter converter;
    Frame frame;
    BufferedImage buffer;
    ByteArrayInputStream bais;
    byte[] data;
    public Deserialize(){
        converter = new Java2DFrameConverter();
    }
    public Frame deSerialize(byte[] data) throws IOException {
        System.out.println("2");
        bais = new ByteArrayInputStream(data);
        buffer = ImageIO.read(bais);
        frame = converter.convert(buffer);
        return frame;
    }


    @Override
    public void run() {
    }


}
