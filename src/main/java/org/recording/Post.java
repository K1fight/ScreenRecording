package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Post {
    Thread t1,t2,t3;
    int counter;
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    SerializeFrameJava buffer;
    ExecutorService ES;
    ThreadPoolExecutor TPE;


    public Post() throws IOException, ClassNotFoundException {

//        server = new ServerSocket(9999);
//        System.out.println("Start listening");
//        s = server.accept();
//        System.out.println("receive connection");
//        output = new ObjectOutputStream(s.getOutputStream());
          TPE = new ThreadPoolExecutor(4,12,10L, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());

    }
    public void start(Frame frame) throws IOException {
        TPE.submit(()->{
                new SerializeFrameJava(frame).start();
            System.out.println("name :" +  Thread.currentThread().getName());
                }
        );
    }
    public void close() throws IOException {
        output.close();
        server.close();
    }


}
