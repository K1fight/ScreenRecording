package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Post {
    static Post post;
    Thread t;

    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    MyThreadspool pool;
    BlockingQueue<byte[]> buffer;
    long count,start,end;
    public static Post getInstance() throws IOException, ClassNotFoundException {
        if(post==null){
            post = new Post();
            return post;
        }
        return post;
    }

    private Post() throws IOException, ClassNotFoundException {
        start = System.nanoTime();
        buffer = new LinkedBlockingQueue<>();

        server = new ServerSocket(10200);
        System.out.println("Start listening");
        s = server.accept();
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());
        pool = new MyThreadspool();

    }
    public synchronized void receive(byte[] data) throws InterruptedException {
        buffer.put(data);
        count++;
        System.out.println(count);
    }
    public void send(){
        t = new Thread("1"){
            @Override
            public void run(){
                while (!interrupted()){
                    try {
                        output.writeObject(buffer.take());
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.start();
    }
    public void close() throws IOException, InterruptedException {
        end = System.nanoTime();
        while (!pool.isEmpty());
        while (!buffer.isEmpty());
        pool.quit();
        output.close();
        server.close();
        t.interrupt();
    }


}
