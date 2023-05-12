package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.recording.Post;
import org.recording.UI;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public  class  SerializeFrameJava{
    private static final long serialVersionUID = 1L;
    ByteArrayOutputStream baos;
    Java2DFrameConverter converter;
    BufferedImage buffer;
    byte[] data;
    Post post;
    boolean status;
    public SerializeFrameJava() throws IOException, ClassNotFoundException {
        converter = new Java2DFrameConverter();
        post = Post.getInstance();
        status = false;
    }
    public void serialize(Frame frame) throws IOException {
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"png",baos);
        data = baos.toByteArray();
        status = true;
    }
    public void send() throws IOException, InterruptedException {
        post.receive(data);
        status =false;
    }
}
