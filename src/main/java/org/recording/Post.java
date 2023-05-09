package org.recording;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.tools.SerializeFrameJava;;
import org.tools.Serialize;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Post {
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    Serialize serialize;


    public Post() throws IOException, ClassNotFoundException {
        serialize = new SerializeFrameJava();
        server = new ServerSocket(9999);
        System.out.println("Start listening");
        s = server.accept();
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());
    }
    public void start(Frame frame) throws IOException {
        output.write(serialize.serialize(frame));
        output.flush();
        output.close();
        server.close();

    }


}
