package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * created by on 2021/9/20
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-09-20-16:35
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = username.getText().toString().trim();
                String strPassword = password.getText().toString().trim();

                OkHttpUtils.post().url(Constant.LOGIN_USERNAME_PASSWORD).addParams("ulUsername", strUsername).addParams("ulPassword", strPassword)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(response);
                                    Log.i(TAG, "转Json===++: " + jsonObject1);
                                    if (jsonObject1.optString("code").equals("200")) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                login.setText(jsonObject1.optString("data"));
                                            }
                                        });
                                    } else if (jsonObject1.optString("code").equals("401")){
                                        login.setText(jsonObject1.optString("data"));
                                    }else {
                                        Toast.makeText(LoginActivity.this, "用户名或密码不正确！", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }
}
