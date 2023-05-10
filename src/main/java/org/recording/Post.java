package org.recording;

import org.bytedeco.javacv.Frame;
import org.tools.SerializeFrameJava;;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Post {
    Thread t1,t2,t3;
    int counter;
    ServerSocket server;
    Socket s;
    ObjectOutputStream output;
    SerializeFrameJava buffer;
    ExecutorService ES;


    public Post() throws IOException, ClassNotFoundException {

//        server = new ServerSocket(9999);
//        System.out.println("Start listening");
//        s = server.accept();
//        System.out.println("receive connection");
//        output = new ObjectOutputStream(s.getOutputStream());
        ES = Executors.newCachedThreadPool();
        counter = 0;
    }
    public void start(Frame frame) throws IOException {
        ES.submit(new SerializeFrameJava(frame));
    }
//    public boolean isAlive(){
//
//        if(t1.isAlive()&&t2.isAlive()&&t3.isAlive()){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    public void close() throws IOException {
        output.close();
        server.close();
    }


}
