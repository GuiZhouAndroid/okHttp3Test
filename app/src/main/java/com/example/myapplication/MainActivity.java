package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        /** 发送一个get请求 */
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username","张松")
                .add("password","root")
                .build();
        //创建一个Request

        final Request request = new Request.Builder()
                .url("https://www.lpssfxy.work/api/user/doLogin")
//                .url("https://www.lpssfxy.work/api/json/list")
//                .url("https://www.lpssfxy.work/api/user/login?username=张松&password=root")
//                .url("https://www.lpssfxy.work/api/user/verify/account?username=张松&password=root")
                .post(requestBody)
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);
        //异步方式请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String res = response.body().string();
                    Log.i(TAG, "服务器返回数据: ==="+res);
                    try {
                        JSONObject finalJson = new JSONObject(res);
                        Log.i(TAG, "转成Json数据===: "+finalJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    throw new IOException("发生错误:" + response.body().string());
                }


//                try {
//                    JSONObject finalJson = new JSONObject(res);
//                    String token = finalJson.optString("token");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(token);
//                        }
//
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
        //取消请求
//        call.cancel();

    }
}