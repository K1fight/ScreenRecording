package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SerializeFrameJava implements Runnable{
    Thread t;
    ByteArrayOutputStream baos;
    Java2DFrameConverter converter;
    BufferedImage buffer;
    public SerializeFrameJava(){
        converter = new Java2DFrameConverter();
    }
    Frame frame;
    byte[] data;
    public void setFrame(Frame frame){
        this.frame = frame;
    }

    @Override
    public void run() {
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        try {
            ImageIO.write(buffer,"png",baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data = baos.toByteArray();
    }

    public byte[] start() {
        if(t == null){
            t = new Thread(this,"1");
            t.start();
        }
        return data;
    }
}
