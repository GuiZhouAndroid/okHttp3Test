package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.okhttp.UtilsOKHttp;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textView;
    private String result;
    private JSONObject finalJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));

        textView = findViewById(R.id.tv);

//        HashMap<String, String> map = new HashMap<>();
//        map.put("pagesize", "20");
//        map.put("page", "6");
//        map.put("key", "6b730ff75e457e11a7d06287ef1738f7");
//
//        String url ="http://v.juhe.cn/joke/content/text.php";
//        //new call
//        UtilsOKHttp.getInstance(this).post(url, map, new UtilsOKHttp.OKCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i(TAG, "onSuccess: "+result.toString());
//            }
//
//            @Override
//            public void onProcess(int i) {
//                Log.i(TAG, "onProcess: "+i);
//            }
//
//            @Override
//            public void onFail(String failResult) {
//                Log.i(TAG, "onProcess: "+failResult);
//            }
//        });
    }

public void doLogout(View view){
    App.cookie.clear();
    String url ="https://www.lpssfxy.work/api/user/doLogout";
    OkHttpUtils.post().url(url).build().execute(new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
        }
        @Override
        public void onResponse(String response, int id) {
            Log.i(TAG, "onResponse===++: "+response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(response);
                }
            });
        }
    });

}
    public void token(View view) {
        String url ="https://www.lpssfxy.work/api/user/getTokenValue";
        OkHttpUtils.post().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, "onResponse===++: "+response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(response);
                    }
                });
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//
//                    //创建okHttpClient对象
//                    OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(App.getInstance().cookie).build();
//                    RequestBody requestBody = new FormBody.Builder().build();
//
//                    Request request = new Request.Builder()
//                            .url("https://www.lpssfxy.work/api/user/isLogin")
////                            .url("https://www.lpssfxy.work/api/user/getTokenValue")
////                            .addHeader("Cookie","satoken=AvewQmjzcVVKEZak6BQ6C0kyYgxQxiCpzI9mERl3YjnF996tU9DXXgJJlaN30SQw")//添加请求头的身份认证Token
//                            .post(requestBody)
//                            .build();
//
//                    //4.创建一个call对象,参数就是Request请求对象
//                    Call call2 = okHttpClient.newCall(request);
//
//                    Response response = call2.execute();
//
//                    result = response.body().string();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(result);
//                        }
//
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public void tv(View view) {
        String url ="https://www.lpssfxy.work/api/user/isLogin";

        OkHttpUtils.post().url(url).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse===++: "+response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(response);
                            }
                        });
                    }
                });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ClearableCookieJar cookie = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MainActivity.this));
//                    Log.i(TAG, "cookie============: " + cookie);
//                    //创建okHttpClient对象
//                    OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookie).build();
//                    RequestBody requestBody = new FormBody.Builder().build();
//                    Request request = new Request.Builder()
//                            .url("https://www.lpssfxy.work/api/user/isLogin")
//                            //.url("https://www.lpssfxy.work/api/user/getTokenValue")
//                            .post(requestBody)
//                            .build();
//
//                    //4.创建一个call对象,参数就是Request请求对象
//                    Call call2 = okHttpClient.newCall(request);
//
//                    Response response = null;
//
//                    response = call2.execute();
//
//
//                    result = response.body().string();
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(result);
//                        }
//
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    public void login(View view) {

        String url ="https://www.lpssfxy.work/api/user/doLogin";
        OkHttpUtils.post().url(url).addParams("username", "张松").addParams("password", "root")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse===++: "+response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(response);
                            }
                        });
                    }
                });

//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", "张松");
//        map.put("password", "root");
//        String url ="https://www.lpssfxy.work/api/user/doLogin";
//        //new call
//        UtilsOKHttp.getInstance(this).post(url, map, new UtilsOKHttp.OKCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i(TAG, "onSuccess: "+result.toString());
//            }
//
//            @Override
//            public void onProcess(int i) {
//                Log.i(TAG, "onProcess: "+i);
//            }
//
//            @Override
//            public void onFail(String failResult) {
//                Log.i(TAG, "onProcess: "+failResult);
//            }
//        });


//        ClearableCookieJar cookie = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
//        Log.i(TAG, "cookie============: " + cookie);
//        //1.创建OkHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().cookieJar(cookie).build();
//        //2.通过new MultipartBody build() 创建requestBody对象，
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("username", "张松");//传递键值对参数
//        builder.addFormDataPart("password", "root");
////        RequestBody requestBody = new FormBody.Builder()
////                .add("username", "张松")//传递键值对参数
////                .add("password", "root")
////                .build();
//        //3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
//        final Request request = new Request.Builder()
//                .url("https://www.lpssfxy.work/api/user/doLogin")
////                .url("https://www.lpssfxy.work/api/json/list")
////                .url("https://www.lpssfxy.work/api/user/login?username=张松&password=root")
////                .url("https://www.lpssfxy.work/api/user/verify/account?username=张松&password=root")
//                .post(builder.build())
//                .build();
//
//        //new call
//        Call call = mOkHttpClient.newCall(request);
//
//        //5.请求加入调度,重写回调方法，异步方式请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String res = response.body().string();
//                    response.headers();
//                    try {
//                        finalJson = new JSONObject(res);
//                        Log.i(TAG, "转成Json数据===: " + finalJson);
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                textView.setText(res);
//                            }
//                        });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    throw new IOException("发生错误:" + response.body().string());
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                Log.d(TAG, "onFailure: " + e.getMessage());
//            }
//        });
    }


    /**
     * 笑话百科
     */
    public void post笑话() {
        /** 发送一个get请求 */
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //2.通过new MultipartBody build() 创建requestBody对象，
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

//        builder.addFormDataPart("page", "6");//传递键值对参数
//        builder.addFormDataPart("pagesize", "20");
//        builder.addFormDataPart("key","6b730ff75e457e11a7d06287ef1738f7")
        RequestBody requestBody = new FormBody.Builder()
                .add("page", "6")//传递键值对参数
                .add("pagesize", "20")
                .add("key", "6b730ff75e457e11a7d06287ef1738f7")
                .build();
        //创建一个Request

        final Request request = new Request.Builder()
                .url("http://v.juhe.cn/joke/content/text.php")
//                .url("https://www.lpssfxy.work/api/json/list")
//                .url("https://www.lpssfxy.work/api/user/login?username=张松&password=root")
//                .url("https://www.lpssfxy.work/api/user/verify/account?username=张松&password=root")
//                .header("Cookie", "xBxsa9cd6FAbc9OVyC27hzlDLoymluA8ghVvucMW1NjRL4K3Mxm8h2BvbuLbF9sl")//添加请求头的身份认证Token
                .post(requestBody)
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);
        //异步方式请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String res = response.body().string();
                    Log.i(TAG, "服务器返回数据: ===" + res);
                    try {
                        finalJson = new JSONObject(res);
                        Log.i(TAG, "转成Json数据===: " + finalJson);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(res);
                            }

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new IOException("发生错误:" + response.body().string());
                }

            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
    }
}