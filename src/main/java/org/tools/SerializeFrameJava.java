package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SerializeFrameJava implements Serialize {
    ByteArrayOutputStream baos;
    Java2DFrameConverter converter;
    BufferedImage buffer;
    public SerializeFrameJava(){
        converter = new Java2DFrameConverter();
    }

    @Override
    public byte[] serialize(Frame frame) throws IOException {
        baos = new ByteArrayOutputStream();
        buffer = converter.convert(frame);
        ImageIO.write(buffer,"png",baos);
        return baos.toByteArray();
    }

    @Override
    public Frame deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        buffer = ImageIO.read(bais);
        return converter.convert(buffer);

    }
}
