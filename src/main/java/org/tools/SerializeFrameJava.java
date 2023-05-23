package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.transmission.Post;
import org.transmission.PostBuffer;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public  class  SerializeFrameJava{
    private static final long serialVersionUID = 1L;
    ByteArrayOutputStream baos;
    Java2DFrameConverter converter;
    BufferedImage buffer;
    byte[] data;
    PostBuffer postBuffer;
    boolean status;
    public SerializeFrameJava() throws IOException, ClassNotFoundException {
        converter = new Java2DFrameConverter();
        postBuffer = PostBuffer.getInstance();
        status = false;
    }
    public void serialize(Frame frame) throws IOException {
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"jpg",baos);
        data = baos.toByteArray();
        status = true;
    }
    public void send() throws IOException, InterruptedException {
        postBuffer.setSendData(data);
        status =false;
    }
}
