package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Deserialize implements Runnable{
    Java2DFrameConverter converter;
    Frame frame;
    BufferedImage buffer;
    byte[] data;
    public Deserialize(){
        converter = new Java2DFrameConverter();
    }


    @Override
    public void run() {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            buffer = ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame =  converter.convert(buffer);
    }


}
