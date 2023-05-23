package org.transmission;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Post {
    static Post post;

    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    public static Post getInstance() throws IOException, ClassNotFoundException {
        if(post==null){
            post = new Post();
            return post;
        }
        return post;
    }

    private Post() throws IOException {
        server = new ServerSocket(10200);
        System.out.println("Start listening");
        s = server.accept();
        s.setSendBufferSize(2500*1024);
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());

    }

    public void send(byte[] data) throws IOException {
        output.writeObject(data);
    }
    public void close() throws IOException, InterruptedException {
        output.close();
        server.close();
    }


}
