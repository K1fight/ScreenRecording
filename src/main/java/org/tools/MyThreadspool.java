package org.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadspool {
    private static final int MAX_THREADS = 12;
    private static final int MAX_TASKS = 100;
    private final BlockingQueue<Runnable> taskQueue;
    private final Set<>


    public MyThreadspool() {
        this.taskQueue = new LinkedBlockingQueue<>();
    }
    private class WorkThread extends Thread{

    }
}
