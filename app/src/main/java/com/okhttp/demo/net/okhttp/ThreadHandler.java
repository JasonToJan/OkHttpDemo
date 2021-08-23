package com.okhttp.demo.net.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHandler {
    private static Handler sMainHandler;
    private static ExecutorService mThreadPool = Executors.newFixedThreadPool(2);


    static {
        sMainHandler = new Handler(Looper.getMainLooper());
    }

    public static void post(Runnable runnable) {
        sMainHandler.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        sMainHandler.postDelayed(runnable, delayMillis);
    }

    public static void execute(Runnable runnable) {
        mThreadPool.execute(runnable);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}