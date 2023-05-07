package org.receving;

import org.bytedeco.javacv.Frame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Get {
    Socket socket;
    ObjectInputStream objectInputStream;
    public Get() throws IOException {
        System.out.println("start connecting");
        socket = new Socket("localhost",9999);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
    public void start() throws IOException, ClassNotFoundException {
        Frame frame = (Frame)objectInputStream.readObject();
    }
}
