package org.receving;

import org.tools.Deserialize;
import org.tools.MyThreadspool2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Get {
    private static Get get;
    Socket socket;
    ObjectInputStream objectInputStream;
    Deserialize de;
    LinkedList<byte[]> buffer;
    MyThreadspool2 pool;
    LinkedList<BufferedImage> bufferedImages;

    public static Get getInstance() throws IOException {
        if(get == null){
            get = new Get();
            return get;
        }
        return get;
    }
    private Get() throws IOException{
        pool = new MyThreadspool2();
        System.out.println("start connecting");
        socket = new Socket("192.168.100.112",9999);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        buffer = new LinkedList<>();
        bufferedImages = new LinkedList<>();
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("2");
        pool.execute(new Deserialize(buffer.getFirst()));
        buffer.removeFirst();
    }
    public void receive(BufferedImage image){
        System.out.println("1");
        bufferedImages.add(image);
    }
    public BufferedImage getFirst(){
        return this.bufferedImages.getFirst();

    }
    public void close() throws IOException {
        objectInputStream.close();
        socket.close();
    }
    public boolean getEmpty() throws IOException, ClassNotFoundException {
        buffer.add((byte[])objectInputStream.readObject());
        return buffer.isEmpty();
    }
    public boolean getEmptyImg(){
        return bufferedImages.isEmpty();
    }

}
