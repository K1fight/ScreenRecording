package org.transmission;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PostBuffer {
    private static PostBuffer ts;
    Thread t1,t2;
    MyThreadspool pool;
    Post post;
    BlockingQueue<Frame> task;
    BlockingQueue<byte[]> sendData;
    public static PostBuffer getInstance() throws IOException, ClassNotFoundException {
        if(ts==null){
            ts = new PostBuffer();
            return ts;
        }
        return ts;
    }
    private PostBuffer() throws IOException, ClassNotFoundException {
        task = new LinkedBlockingQueue<>();
        post = Post.getInstance();
    }
    public void setData(Frame frame) throws InterruptedException {
        task.put(frame.clone());
    }
    public synchronized void setSendData(byte[] data) throws InterruptedException {
        sendData.put(data);
    }
    public void send(){
        t1 = new Thread("t1"){
            @Override
            public void run(){
                try {
                    post.send(sendData.take());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
    }

    private void execute(){
        t2 = new Thread("t2"){
            @Override
            public void run(){
                try {
                    pool.execute(task.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t2.start();
    }
    public void close() throws IOException, InterruptedException {
        post.close();
        t1.interrupt();
        t2.interrupt();
    }
}
