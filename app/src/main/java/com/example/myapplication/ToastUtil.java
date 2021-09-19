package com.example.myapplication;

import android.widget.Toast;

/**
 * created by on 2021/8/23
 * 描述：Toast提示工具类
 *
 * @author ZSAndroid
 * @create 2021-08-23-14:30
 */
public class ToastUtil {
    private static Toast sToast;

    public static void showToast(final String text) {
        if (sToast != null) {
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_SHORT);
        } else {
            sToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        }
        sToast.show();
    }

    /**
     * 只显示一次
     * @param key
     * @param value
     */
    public static void showOnceToast(String key, String value) {
        if (!SharePreferenceUtil.getInstance().getBoolean(key, false)) {
            showToast(value);
            SharePreferenceUtil.getInstance().putBoolean(key, true);
        }
    }
}
