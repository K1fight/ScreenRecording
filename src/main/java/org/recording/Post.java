package org.recording;

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
    Thread t,t1;

    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    MyThreadspool pool;
    BlockingQueue<byte[]> buffer;
    BlockingQueue<Frame> framesBuffer;
    long count,start,end;
    public static Post getInstance() throws IOException, ClassNotFoundException {
        if(post==null){
            post = new Post();
            return post;
        }
        return post;
    }

    private Post() throws IOException {
        start = System.nanoTime();
        buffer = new LinkedBlockingQueue<>();
        framesBuffer = new LinkedBlockingQueue<>();
        server = new ServerSocket(10200);
        System.out.println("Start listening");
        s = server.accept();
        s.setSendBufferSize(2500*1024);
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());

    }
    public void startPool() throws IOException, ClassNotFoundException {
        pool = MyThreadspool.getInstance(3,100);
    }
    public synchronized void receive(byte[] data) throws InterruptedException {
//        System.out.println(data.length);
        buffer.put(data);
//        System.out.println("receive:" +count);
    }
    public void setFramesBuffer(Frame frame) throws InterruptedException {
        framesBuffer.put(frame.clone());
    }
    public void putFrame(){
        t1 = new Thread("t1"){
            @Override
            public void run() {
                while (!interrupted()){
                    try {
                        pool.execute(framesBuffer.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t1.start();
    }
    public void send(){
        t = new Thread("1"){
            @Override
            public void run(){
                try {
                    s.setSoTimeout(10000);
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }
                int counter = 0;
                while (!interrupted()){
                    try {

                                byte[] temp = buffer.take();
                                output.writeObject(temp);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
    public void close() throws IOException, InterruptedException {
        end = System.nanoTime();
        System.out.println(count/((end-start)/1_000_000_000));
        while (!pool.isEmpty());
        pool.quit();
        output.close();
        server.close();
        t.interrupt();
    }


}
