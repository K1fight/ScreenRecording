package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.*;

public class Post {
    Thread t1,t2,t3;
    int counter;
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    SerializeFrameJava buffer;
    ThreadPoolExecutor TPE;
    LinkedList<Frame[]> bufferFrame;

    public Post() throws IOException, ClassNotFoundException {

//        server = new ServerSocket(9999);
//        System.out.println("Start listening");
//        s = server.accept();
//        System.out.println("receive connection");
//        output = new ObjectOutputStream(s.getOutputStream());
//        bufferFrame = new LinkedList<>();
//        buffer = new SerializeFrameJava();
          TPE = new ThreadPoolExecutor(12,24,10L, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(20));

    }
    public void start(Frame frame) throws IOException {
        Thread t1 = new Thread(new SerializeFrameJava(frame),"1");

    }
    public void close() throws IOException {
        output.close();
        server.close();
    }


}
