package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.transmission.GetBuffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Deserialize{
    Java2DFrameConverter converter;
    BufferedImage buffer;
    ByteArrayInputStream bais;
    GetBuffer gb;
    boolean status;
    public Deserialize() throws IOException {
        converter = new Java2DFrameConverter();
        status = false;
        gb = GetBuffer.getInstance();
    }
    public void deSerialize(byte[] data) throws IOException {
        bais = new ByteArrayInputStream(data);
        buffer = ImageIO.read(bais);
        status = true;
    }
    public void send() throws InterruptedException {
        gb.setData(buffer);
        status = false;
    }


}
