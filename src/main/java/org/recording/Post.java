package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Post {
    static Post post;
    Thread t;
    InetAddress address;

    DatagramSocket server;
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
        buffer = new LinkedBlockingQueue<>(1);
        server = new DatagramSocket();
        address = InetAddress.getByName("192.168.100.176");
        System.out.println("Start listening");

        System.out.println("receive connection");
        pool = new MyThreadspool();

    }
    public synchronized void receive(byte[] data) throws InterruptedException {
        buffer.put(data);
        count++;
    }
    public void send(){
        t = new Thread("1"){
            @Override
            public void run(){
                while (!interrupted()){
                    try {
                        byte[] temp = buffer.take();
                        DatagramPacket packet = new DatagramPacket(temp,temp.length,address,10200);
                        server.send(packet);
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.start();
    }
    public void close() throws IOException, InterruptedException {
        end = System.nanoTime();
        System.out.println(count);
        while (!pool.isEmpty());
        pool.quit();
        server.close();
        t.interrupt();
    }


}
