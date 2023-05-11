package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Deserialize{
    Java2DFrameConverter converter;
    Frame frame;
    BufferedImage buffer;
    ByteArrayInputStream bais;
    byte[] data;
    public Deserialize(){
        converter = new Java2DFrameConverter();
    }
    public BufferedImage deSerialize(byte[] data) throws IOException {
        bais = new ByteArrayInputStream(data);
        buffer = ImageIO.read(bais);
        return buffer;
    }


}
