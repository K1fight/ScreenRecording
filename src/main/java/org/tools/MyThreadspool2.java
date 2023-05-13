package org.tools;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadspool2 {
    private static MyThreadspool2 pool;
    private static final int MAX_THREADS = 24;
    private static final int MAX_TASKS = 600;
    private final BlockingQueue<byte[]> taskQueue;
    private final Set<WorkThread> threads;
    private final BlockingQueue<Deserialize> order;
    private int thread;
    private int task;
    private boolean quit;
    public static MyThreadspool2 getInstance(int thread,int tasks) throws IOException {
        if(pool==null){
            pool = new MyThreadspool2(thread,tasks);
            return pool;
        }
        return pool;
    }
    public static MyThreadspool2 getInstance() throws IOException {
        if(pool==null){
            pool = new MyThreadspool2();
            return pool;
        }
        return pool;
    }

    private MyThreadspool2() throws IOException {
        this(MAX_THREADS,MAX_TASKS);
    }
    private MyThreadspool2(int thread,int task) throws IOException {
        if(thread<=0){
            this.thread = MAX_THREADS;
        }
        if(task<=0){
            this.task = MAX_TASKS;
        }
        this.taskQueue = new LinkedBlockingQueue<>(task);
        this.order = new LinkedBlockingQueue<>(24);
        threads = new HashSet<>();
        this.task = task;
        this.thread = thread;
        DaemenThread daemen = new DaemenThread("daemen-1");
        daemen.start();
        for(int i = 0;i<thread;i++){
            WorkThread workThread = new WorkThread("thread"+i);
            workThread.start();
            threads.add(workThread);
        }
    }
    public void execute(byte[] task){
        try{
            taskQueue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void destory(){
        for(WorkThread temp:threads){
            temp.stopWorker();
            temp = null;
        }
        threads.clear();
    }
    public boolean isEmpty(){
        return taskQueue.isEmpty();
    }
    public void quit() throws InterruptedException {
        this.quit = true;
        Thread.sleep(5000);
        destory();

    }

    private class WorkThread extends Thread{
        Deserialize deserialize;
        public WorkThread(String name) throws IOException {
            super();
            deserialize = new Deserialize();
            setName(name);
        }
        @Override
        public void run(){
            while (!interrupted()) {
                if(!quit){
                    try {
                        byte[] temp = taskQueue.take();
                        order.put(deserialize);
                        deserialize.deSerialize(temp);
                    } catch (Exception e) {
                        interrupt();
                        e.printStackTrace();
                    }
                }else{
                    interrupt();
                }
            }
        }
        public void stopWorker(){
            interrupt();
        }
    }
    private class DaemenThread extends Thread{
        public DaemenThread(String name){
            super();
            setName(name);
        }
        @Override
        public void run(){
            while (!interrupted()){
                try {
                    Deserialize temp = order.take();
                    while (true){
                        if(temp.status){
                            temp.send();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
