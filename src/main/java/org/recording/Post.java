package org.recording;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.tools.FST;
import org.tools.SerializeFrameJava;;
import org.tools.Serialize;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Post {
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    SerializeFrameJava buffer;


    public Post() throws IOException, ClassNotFoundException {
        buffer = new SerializeFrameJava();
//        server = new ServerSocket(9999);
//        System.out.println("Start listening");
//        s = server.accept();
//        System.out.println("receive connection");
//        output = new ObjectOutputStream(s.getOutputStream());
    }
    public void start(Frame frame) throws IOException {
        buffer.setFrame(frame);
        buffer.start();
    }
    public void close() throws IOException {
        output.close();
        server.close();
    }


}
