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

    public static Get getInstance() throws IOException {
        if(get == null){
            get = new Get();
            return get;
        }
        return get;
    }
    private Get() throws IOException{
        System.out.println("start connecting");
        socket = new Socket("127.0.0.1",10200);
        socket.setReceiveBufferSize(2500*1024);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
    public void close() throws IOException {
        objectInputStream.close();
        socket.close();
    }
    public byte[] receive() throws IOException, ClassNotFoundException {
         return (byte[]) objectInputStream.readObject();
    }
}
