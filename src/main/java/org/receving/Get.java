package org.receving;

import org.tools.Deserialize;
import org.tools.MyThreadspool2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Get {
    private static Get get;
    DatagramSocket socket;
    LinkedList<byte[]> buffer;
    MyThreadspool2 pool;
    BlockingQueue<BufferedImage> bufferedImages;
    byte[] bu;

    public static Get getInstance() throws IOException {
        if(get == null){
            get = new Get();
            return get;
        }
        return get;
    }
    private Get() throws IOException{
        bu = new byte[2048];
        pool = new MyThreadspool2();
        System.out.println("start connecting");
        socket = new DatagramSocket(10200);
        buffer = new LinkedList<>();
        bufferedImages = new LinkedBlockingQueue<>();
    }

    public void start() throws IOException, ClassNotFoundException {
        pool.execute(new Deserialize(buffer.getFirst()));
        buffer.removeFirst();
    }
    public synchronized void receive(BufferedImage image){
        bufferedImages.add(image);
    }
    public BufferedImage getFirst() throws InterruptedException {
        return bufferedImages.take();

    }
    public void close() throws IOException {
        socket.close();
    }
    public boolean getEmpty() throws IOException, ClassNotFoundException {
        DatagramPacket packet = new DatagramPacket(bu,bu.length);
        socket.receive(packet);
        return buffer.isEmpty();
    }

}
