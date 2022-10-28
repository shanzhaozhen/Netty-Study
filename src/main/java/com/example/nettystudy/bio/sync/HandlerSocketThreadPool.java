package com.example.nettystudy.bio.sync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-27
 * @Description:
 */
public class HandlerSocketThreadPool {

    // 线程池
    private final ExecutorService executor;

    public HandlerSocketThreadPool(int maxPoolSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(
                3, // 8
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }

    //提交一个任务
    public void execute(Runnable task) {
        this.executor.execute(task);
    }

}