package org.receving;

import org.tools.Deserialize;
import org.tools.MyThreadspool2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Get {
    private static Get get;
    Socket socket;
    ObjectInputStream objectInputStream;
    Deserialize de;
    BlockingQueue<byte[]> buffer;
    MyThreadspool2 pool;
    BlockingQueue<BufferedImage> bufferedImages;
    Thread t;

    public static Get getInstance() throws IOException {
        if(get == null){
            get = new Get();
            return get;
        }
        return get;
    }
    private Get() throws IOException{

        pool = MyThreadspool2.getInstance();
        System.out.println("start connecting");
        socket = new Socket("127.0.0.1",10200);
        socket.setReceiveBufferSize(1024*1024);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        buffer = new LinkedBlockingQueue<>();
        bufferedImages = new LinkedBlockingQueue<>();
    }

    public void start() throws IOException, ClassNotFoundException, InterruptedException {
        pool.execute(new Deserialize(buffer.take()));
    }
    public synchronized void receive(BufferedImage image){
        bufferedImages.add(image);
    }
    public BufferedImage getFirst() throws InterruptedException {
        return bufferedImages.take();

    }
    public void close() throws IOException {
        objectInputStream.close();
        socket.close();
        t.interrupt();
    }
//    public boolean getEmpty() throws IOException, ClassNotFoundException {
//        return buffer.isEmpty();
//    }
    public void receiveData(){
        t = new Thread("1"){
            @Override
            public void run(){
                while (!interrupted()){
                    try {
                        buffer.put((byte[])objectInputStream.readObject());
                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.start();
    }
}
