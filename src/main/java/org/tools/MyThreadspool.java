package org.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadspool {
    private static final int MAX_THREADS = 12;
    private static final int MAX_TASKS = 300;
    private final BlockingQueue<SerializeFrameJava> taskQueue;
    private final Set<WorkThread> threads;
    private int thread;
    private int task;
    private boolean quit;

    public MyThreadspool(){
        this(MAX_THREADS,MAX_TASKS);
    }
    public MyThreadspool(int thread,int task) {
        if(thread<=0){
            this.thread = MAX_THREADS;
        }
        if(task<=0){
            this.task = MAX_TASKS;
        }
        this.taskQueue = new LinkedBlockingQueue<>(task);
        threads = new HashSet<>();
        this.task = task;
        this.thread = thread;
        for(int i = 0;i<thread;i++){
            WorkThread workThread = new WorkThread("thread"+i);
            workThread.start();
            threads.add(workThread);
        }
    }
    public void execute(SerializeFrameJava task){
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
        public WorkThread(String name){
            super();
            setName(name);
        }
        @Override
        public void run(){
            while (!interrupted()) {
                if(!quit){
                    try {
                        SerializeFrameJava temp = taskQueue.take();

                        temp.serialize();
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
    private class DaemenThread{

    }
}
