package org.receving;

import org.bytedeco.javacv.Frame;
import org.tools.SerializeFrameKyroBuffer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Get {
    Socket socket;
    ObjectInputStream objectInputStream;
    SerializeFrameKyroBuffer serializeFrameKryoB;
    public Get() throws IOException, ClassNotFoundException {
        serializeFrameKryoB = new SerializeFrameKyroBuffer();
        System.out.println("start connecting");
        socket = new Socket("127.0.0.1",9999);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
    public void start() throws IOException{
        Frame frame = serializeFrameKryoB.deserialize(objectInputStream.readAllBytes());
        System.out.println(frame.imageWidth);
        System.out.println(frame.imageHeight);
        System.out.println(frame.toString());
        objectInputStream.close();
        socket.close();
    }
}
