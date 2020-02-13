package com.robertomanfreda.rlogger.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggingQueue {
    // Singleton
    private static final LoggingQueue loggingQueue = new LoggingQueue();

    public static LoggingQueue getInstance() {
        return loggingQueue;
    }
    // --

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final Queue<Runnable> queue = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            while (true) {
                if (queue.size() > 0) executorService.execute(queue.poll());
            }
        }).start();
    }

    void enqueue(Runnable r) {
        queue.add(r);
    }
}