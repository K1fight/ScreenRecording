package org.test;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.receving.Get;
import org.recording.Post;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        Frame frame = converter.convert(ImageIO.read(new File("./2.bmp")));
        System.out.println(frame.imageHeight);
        Post post = new Post();
        post.start(frame);

    }
}
