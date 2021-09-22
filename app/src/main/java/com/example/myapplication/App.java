package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.okhttp.UtilsOKHttp;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * created by on 2021/9/15
 * 描述：
 *
 * @author 1
 * @create 2021-09-15-10:59
 */
public class App extends Application {
    private static App myApp;
    public static ClearableCookieJar cookie;

    public static App getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        cookie = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookie)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
