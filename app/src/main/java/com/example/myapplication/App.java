package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.okhttp.OkHttpUtil;

/**
 * created by on 2021/9/15
 * 描述：
 *
 * @author 1
 * @create 2021-09-15-10:59
 */
public class App extends Application {
    private static App myApp;

    public static App getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init(this);
    }
}
