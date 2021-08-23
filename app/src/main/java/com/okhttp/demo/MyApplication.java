package com.okhttp.demo;

import android.app.Application;

/**
 * desc:
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/8/21 11:21
 * contact: jason1211241203@gmail.com
 **/
public class MyApplication extends Application {

    private static MyApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
