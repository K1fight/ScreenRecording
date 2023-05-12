package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Post {
    static Post post;
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    MyThreadspool pool;
    SerializeFrameJava ser;
    public static Post getInstance() throws IOException, ClassNotFoundException {
        if(post==null){
            post = new Post();
            return post;
        }
        return post;
    }

    private Post() throws IOException{

        server = new ServerSocket(9999);
        System.out.println("Start listening");
        s = server.accept();
        System.out.println("receive connection");
        output = new ObjectOutputStream(s.getOutputStream());
        pool = new MyThreadspool();

    }
    public void start(Frame[] frame) throws IOException, InterruptedException, ClassNotFoundException {
        for(Frame temp:frame){
            ser = new SerializeFrameJava(temp.clone());
            pool.execute(ser);
        }
    }
    public synchronized void send(byte[] data) throws IOException {
        output.writeObject(data);
    }
    public void close() throws IOException, InterruptedException {
        while (!pool.isEmpty());
        pool.quit();
        output.close();
        server.close();
    }


}
