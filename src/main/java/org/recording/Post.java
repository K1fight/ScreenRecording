package org.recording;

import org.bytedeco.javacv.Frame;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Post {
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    public Post() throws IOException {
        server = new ServerSocket(9999);
        System.out.println("Start listening");
        s = server.accept();
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());
    }
    public void start(Frame frame) throws IOException {
        output.writeObject(frame);
        output.flush();

    }


}
