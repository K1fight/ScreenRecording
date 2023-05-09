//package org.tools;
//
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.SerializerFactory;
//import com.esotericsoftware.kryo.io.Input;
//import com.esotericsoftware.kryo.io.Output;
//import com.esotericsoftware.kryo.serializers.JavaSerializer;
//import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.OpenCVFrameConverter;
//import org.bytedeco.opencv.opencv_core.Mat;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//
//public class SerializeFrameKyroBuffer {
//    Class clzN;
//    OpenCVFrameConverter converter = new OpenCVFrameConverter.ToMat();
//    Kryo kryo ;
//    Mat mat;
//    ByteArrayOutputStream baos;
//    ByteArrayInputStream bais;
//    Frame frame;
//    byte[] data ;
//    public SerializeFrameKyroBuffer() throws ClassNotFoundException {
////        clzN = Class.forName("org.bytedeco.javacpp.Pointer.NativeDeallocator");
//        kryo = new Kryo();
//        kryo.setReferences(true);
//        kryo.setRegistrationRequired(false);
//        baos = new ByteArrayOutputStream();
//        frame = new Frame();
//        kryo.setDefaultSerializer( new JavaSerializer());
////        SynchronizedCollectionsSerializer.registerSerializers(kryo);
////        kryo.register(org.bytedeco.opencv.opencv_core.Mat.class);
////
////        kryo.register(clzN);
//    }
//    public byte[] serialize(Frame frame){
//        mat = converter.convertToMat(frame);
//        Output output = new Output(baos);
//        kryo.writeClassAndObject(output,mat);
//        output.close();
//        data = baos.toByteArray();
//        return data;
//    }
//    public Frame deserialize(byte[] data){
//        bais = new ByteArrayInputStream(data);
//        Input input = new Input(bais);
//        mat = (Mat) kryo.readClassAndObject(input);
//        input.close();
//        frame = converter.convert(mat);
//        return frame;
//    }
//}
