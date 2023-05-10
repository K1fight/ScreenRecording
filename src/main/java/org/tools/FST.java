package org.tools;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.nustaq.serialization.FSTConfiguration;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class FST implements Serialize {
    FSTConfiguration con;
    String name;
    Frame frame;
    BufferedImage bufferedImage;
    byte[] data;
    Java2DFrameConverter converter;
    public  FST(){
        converter = new Java2DFrameConverter();
        con = FSTConfiguration.createDefaultConfiguration();
        con.registerClass(java.awt.image.BufferedImage.class);
//        con.setShareReferences(false);
    }
    @Override
    public byte[] serialize(Frame frame) throws IOException {
        return con.asByteArray(converter.convert(frame));
    }

//    public void setFrame(Frame frame){
//        this.frame = frame;
//    }
//    public byte[] getData(){
//        return  this.data;
//    }
//
//    @Override
//    public void start() {
//
//    }


    @Override
    public Frame deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        bufferedImage = (BufferedImage) con.asObject(bytes);
        return converter.convert(bufferedImage);
    }

//    public void run() {
//        System.out.println("1");
//       data =  con.asByteArray(this.frame);
//    }
}
