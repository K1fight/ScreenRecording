package org.tools;

import org.bytedeco.javacv.Frame;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadspool {
    private static final int MAX_THREADS = 12;
    private static final int MAX_TASKS = 300;
    private final BlockingQueue<Frame> taskQueue;
    private final Set<WorkThread> threads;
    private final BlockingQueue<SerializeFrameJava> order;
    private int thread;
    private int task;
    private boolean quit;

    public MyThreadspool() throws IOException, ClassNotFoundException {
        this(MAX_THREADS,MAX_TASKS);
    }
    public MyThreadspool(int thread,int task) throws IOException, ClassNotFoundException {
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
    public void execute(Frame task){
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
        public WorkThread(String name) throws IOException, ClassNotFoundException {
            super();
            setName(name);
        }
        @Override
        public void run(){
            SerializeFrameJava serializeFrameJava = null;
            try {
                serializeFrameJava = new SerializeFrameJava();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (!interrupted()) {
                if(!quit){
                    try {
                        Frame temp = taskQueue.take();
                        order.put(serializeFrameJava);
                        serializeFrameJava.serialize(temp);
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
                    SerializeFrameJava temp = order.take();
                    while (true){
                        if(temp.status){
                            temp.send();
                            break;
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
