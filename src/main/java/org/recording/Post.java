package org.recording;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.tools.SerializeFrameKyroBuffer;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Post {
    private static final Java2DFrameConverter converter = new Java2DFrameConverter();
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    SerializeFrameKyroBuffer serializeFrameKryoB;


    public Post() throws IOException, ClassNotFoundException {
        serializeFrameKryoB = new SerializeFrameKyroBuffer();
        server = new ServerSocket(9999);
        System.out.println("Start listening");
        s = server.accept();
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());
    }
    public void start(Frame frame) throws IOException {
        output.write(serializeFrameKryoB.serialize(frame));
        output.flush();
        output.close();
        server.close();

    }


}
