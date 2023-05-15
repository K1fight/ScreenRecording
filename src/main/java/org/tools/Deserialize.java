package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.transmission.Get;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Deserialize{
    Java2DFrameConverter converter;
    BufferedImage buffer;
    ByteArrayInputStream bais;
    Get get;
    byte[] data;
    boolean status;
    public Deserialize() throws IOException {
        converter = new Java2DFrameConverter();
        status = false;
        get = Get.getInstance();
    }
    public void deSerialize(byte[] data) throws IOException {
        bais = new ByteArrayInputStream(data);
        buffer = ImageIO.read(bais);
        status = true;
    }
    public void send() throws InterruptedException {
        get.receive(buffer);
    }


}
