//package org.tools;
//
//import org.bytedeco.javacv.Frame;
//import org.msgpack.core.MessageBufferPacker;
//import org.msgpack.core.MessagePack;
//import org.msgpack.core.MessageUnpacker;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.nio.Buffer;
//import java.nio.ByteBuffer;
//
//public class SerializeFrame implements Serializable {
//
//    private boolean keyFrame;
//    private char pictType;
//    private int imageWidth;
//    private int imageHeight;
//    private int imageDepth;
//    private int imageChannels;
//    private int imageStride;
//    private int sampleRate;
//    private int audioChannels;
//    private int streamIndex;
//    private long timeStamp;
//    private Frame.Type type;
//    private Buffer[] image;
//    private Buffer[] samples;
//    private ByteBuffer data;
//    private Object Opaque;
//
//    public SerializeFrame(Frame frame) {
//        this.keyFrame = frame.keyFrame;
//        this.pictType = frame.pictType;
//        this.imageWidth = frame.imageWidth;
//        this.imageHeight = frame.imageHeight;
//        this.imageDepth = frame.imageDepth;
//        this.imageChannels = frame.imageChannels;
//        this.imageStride = frame.imageStride;
//        this.type = frame.type;
//        this.audioChannels = frame.audioChannels;
//        this.sampleRate = frame.sampleRate;
//        this.timeStamp = frame.timestamp;
//        this.Opaque = frame.opaque;
//        if(frame.image != null){
//            this.image = frame.image;
//        }
//        if(samples!=null){
//            this.samples = frame.samples;
//        }
//        if(data!=null){
//            this.data = frame.data;
//        }
//    }
//
//    public byte[] serialize() throws IOException {
//        MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
//        packer.packInt(imageWidth);
//        packer.packInt(imageHeight);
//        packer.packInt(imageDepth);
//        packer.packInt(imageChannels);
//        packer.packInt(imageStride);
//        packer.packArrayHeader(image[0]);
//
//
//        packer.close();
//
//        return packer.toByteArray();
//    }
//
//    public static SerializeFrame deserialize(byte[] bytes) throws IOException {
//        MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(bytes);
//        Template
//        int imageWidth = unpacker.unpackInt();
//        int imageHeight = unpacker.unpackInt();
//        int format = unpacker.unpackInt();
//        int dataLength = unpacker.unpackBinaryHeader();
//        byte[] data = new byte[dataLength];
//        unpacker.readPayload(data);
//
//        Frame frame = new Frame(imageWidth, imageHeight, format);
//        frame.image[0].put(data);
//
//        return new SerializeFrame(frame);
//    }
//
//    public Frame toFrame() {
//        Frame frame = new Frame(imageWidth, imageHeight, format);
//        if (data != null) {
//            frame.image[0].put(data);
//        }
//        return frame;
//    }
//
//}
