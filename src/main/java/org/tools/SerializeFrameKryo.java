//package org.tools;
//
//import org.bytedeco.javacv.Frame;
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.io.Input;
//import com.esotericsoftware.kryo.io.Output;
//
//
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//
//public class SerializeFrameKryo {
//    Kryo kryo ;
//    Class clzNio,clzFile,clzNaD;
//    byte[] data;
//    ByteArrayOutputStream baos;
//    ByteArrayInputStream bais;
//    Frame frame;
//    public SerializeFrameKryo() throws ClassNotFoundException {
//        clzNio = Class.forName("java.nio.DirectByteBuffer");
//        clzFile = Class.forName("java.io.FileDescriptor");
////        clzNaD = Class.forName("org.bytedeco.javacpp.Pointer.NativeDeallocator");
//
//        kryo = new Kryo();
//        kryo.register(org.bytedeco.javacv.Frame.class);
//        kryo.register(org.bytedeco.javacv.Frame.Type.class);
//        kryo.register(java.nio.Buffer[].class);
//        kryo.register(org.bytedeco.javacpp.Pointer[].class);
//        kryo.register(org.bytedeco.javacpp.BytePointer.class);
//        kryo.register(org.bytedeco.javacpp.Pointer.NativeDeallocator.class);
////        kryo.register(clzNaD);
//        kryo.register(byte[].class);
//        kryo.register(clzNio);
//        kryo.register(clzFile);
//        baos = new ByteArrayOutputStream();
//    }
//    public byte[] serialize(Frame frame){
//        Output output = new Output(baos);
//        kryo.writeClassAndObject(output,frame);
//        output.close();
//        data = baos.toByteArray();
//        return data;
//    }
//    public Frame deserialize(byte[] serializedData){
//        bais = new ByteArrayInputStream(serializedData);
//        Input input = new Input(bais);
//        frame = (Frame) kryo.readClassAndObject(input);
//        input.close();
//        return frame;
//    }
//}
