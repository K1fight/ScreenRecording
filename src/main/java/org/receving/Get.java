package org.receving;

import org.bytedeco.javacv.Frame;
import org.tools.Deserialize;
import org.tools.SerializeFrameJava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Get {
    Socket socket;
    ObjectInputStream objectInputStream;
    Deserialize de;
    LinkedList<byte[]> buffer;
    public Get() throws IOException{
        de= new Deserialize();
        System.out.println("start connecting");
        socket = new Socket("127.0.0.1",9999);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        buffer = new LinkedList<>();
    }
//    public void start() throws IOException, ClassNotFoundException {
//        buffer.add(serialize.deserialize(objectInputStream.readAllBytes()));
//        System.out.println("2");
//    }
    public Frame get() throws IOException, ClassNotFoundException {

        buffer.removeFirst();
        return null;
    }
    public void close() throws IOException {
        objectInputStream.close();
        socket.close();
    }
    public boolean getEmpty() throws IOException, ClassNotFoundException {
        buffer.add((byte[])objectInputStream.readObject());
        return buffer.isEmpty();
    }
}
