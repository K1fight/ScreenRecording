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
    public Frame frame;
    byte[] data;
    Post post;
    boolean status;
    public SerializeFrameJava(Frame frame) throws IOException, ClassNotFoundException {
        converter = new Java2DFrameConverter();
        this.frame = frame;
        post = Post.getInstance();
        status = false;
    }
    public void serialize() throws IOException {
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"png",baos);
        data = baos.toByteArray();
        status = true;
    }
    public void send() throws IOException {
        post.send(data);
    }
}
