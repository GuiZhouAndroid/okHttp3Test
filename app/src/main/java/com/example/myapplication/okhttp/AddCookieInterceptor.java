package com.example.myapplication.okhttp;

import android.text.TextUtils;

import com.example.myapplication.SharePreferenceUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * created by on 2021/8/23
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-08-23-14:22
 */
public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        String cookie = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }


    private String getCookie(String url, String host) {
        if (!TextUtils.isEmpty(url)
                && SharePreferenceUtil.getInstance().contains(url)
                && !TextUtils.isEmpty(SharePreferenceUtil.getInstance().optString(url))) {
            return SharePreferenceUtil.getInstance().optString(url);
        }

        if (!TextUtils.isEmpty(host)
                && SharePreferenceUtil.getInstance().contains(host)
                && !TextUtils.isEmpty(SharePreferenceUtil.getInstance().optString(host))) {
            return SharePreferenceUtil.getInstance().optString(host);
        }
        return null;
    }
}
