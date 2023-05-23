package org.transmission;

import org.receving.Get;
import org.tools.MyThreadspool2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GetBuffer {
    private static GetBuffer gb;
    private Get get;
    private MyThreadspool2 pool;
    private BlockingQueue<byte[]> receivedData;
    private BlockingQueue<BufferedImage> data;
    private Thread t1,t2;
    public static GetBuffer getInstance() throws IOException {
        if(gb == null){
            gb = new GetBuffer();
                return gb;
        }
        return gb;
    }
    private GetBuffer() throws IOException {
        get = Get.getInstance();
        receivedData = new LinkedBlockingQueue<>();
        data = new LinkedBlockingQueue<>();
    }
    public void startMutithreads() throws IOException {
        pool = MyThreadspool2.getInstance(9,100);
        receive();
        execute();
    }
    private void receive(){
        t1 = new Thread("t1G"){
            @Override
            public void run(){
                while (!interrupted()) {
                    try {
                        System.out.println("receive:"+receivedData.size());
                        System.out.println("data:"+data.size());
                        receivedData.put(get.receive());

                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t1.start();
    }
    private void execute(){
        t2 = new Thread("t2G"){
            @Override
            public void run(){
                while (!interrupted()) {
                    try {
                        pool.execute(receivedData.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t2.start();
    }
    public synchronized void setData(BufferedImage bi) throws InterruptedException {
        data.put(bi);
    }
    public BufferedImage getData() throws InterruptedException {
        return data.take();
    }
    public void close() throws IOException {
        get.close();
    }

}
