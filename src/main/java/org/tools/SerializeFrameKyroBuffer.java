package org.tools;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerializeFrameKyroBuffer {
    Class clzB,clzC,clzI,clzI2,clzSB,clzD,clzSS;
    Kryo kryo ;
    Java2DFrameConverter converter;
    BufferedImage bufferedImg;
    ByteArrayOutputStream baos;
    ByteArrayInputStream bais;
    Frame frame;
    byte[] data ;
    public SerializeFrameKyroBuffer() throws ClassNotFoundException {
        clzB = Class.forName("java.awt.image.BufferedImage");
        clzC = Class.forName("java.awt.image.ComponentColorModel");
        clzI = Class.forName("java.awt.color.ICC_ColorSpace");
        clzI2 = Class.forName("java.awt.color.ICC_ProfileRGB");
        clzSB = Class.forName("sun.awt.image.ByteInterleavedRaster");
        clzD = Class.forName("java.awt.image.DataBufferByte");
        clzSS = Class.forName("sun.java2d.StateTrackableDelegate");

        kryo = new Kryo();
        converter = new Java2DFrameConverter();
        baos = new ByteArrayOutputStream();
        frame = new Frame();
        kryo.register(sun.java2d.StateTrackable.State.class);
        kryo.register(java.awt.image.PixelInterleavedSampleModel.class);
        kryo.register(org.tools.FakeBufferedImage.class);
        kryo.register(float[].class);
        kryo.register(short[].class);
        kryo.register(byte[].class);
        kryo.register(int[].class);
        kryo.register(byte[][].class);
        kryo.register(clzB);
        kryo.register(clzC);
        kryo.register(clzI);
        kryo.register(clzI2);
        kryo.register(clzSB);
        kryo.register(clzD);
        kryo.register(clzSS);
    }
    public byte[] serialize(Frame frame){
        bufferedImg = converter.getBufferedImage(frame);
        Output output = new Output(baos);
        kryo.writeClassAndObject(output,bufferedImg);
        output.close();
        data = baos.toByteArray();
        return data;
    }
    public Frame deserialize(byte[] data){
        bais = new ByteArrayInputStream(data);
        Input input = new Input(bais);
        bufferedImg = (BufferedImage) kryo.readClassAndObject(input);
        input.close();
        frame = converter.convert(bufferedImg);
        return frame;
    }
}
