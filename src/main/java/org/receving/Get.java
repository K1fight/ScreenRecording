package org.receving;

import org.bytedeco.javacv.Frame;
import org.tools.Serialize;
import org.tools.SerializeFrameJava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Get {
    Socket socket;
    ObjectInputStream objectInputStream;
    Serialize serialize;
    public Get() throws IOException{
        serialize = new SerializeFrameJava();
        System.out.println("start connecting");
        socket = new Socket("127.0.0.1",9999);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
    public void start() throws IOException, ClassNotFoundException {
        Frame frame = serialize.deserialize(objectInputStream.readAllBytes());
        System.out.println(frame.imageWidth);
        System.out.println(frame.imageHeight);
        System.out.println(frame.toString());
        objectInputStream.close();
        socket.close();
    }
}
