package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.receving.Get;

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
    Get get;
    byte[] data;
    boolean status;
    public Deserialize(byte[] data) throws IOException {
        converter = new Java2DFrameConverter();
        this.data = data;
        status = false;
        get = Get.getInstance();
    }
    public void deSerialize() throws IOException {
        bais = new ByteArrayInputStream(data);
        buffer = ImageIO.read(bais);
        status = true;
    }
    public void send(){
        get.receive(buffer);
    }


}
