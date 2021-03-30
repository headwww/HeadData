package com.head.data;

import android.app.Application;

import com.head.http.HeadHttp;
import com.head.http.cookie.CookieManger;
import com.tencent.mmkv.MMKV;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HeadHttp.init(this);
        HeadHttp.getInstance()
                .debug("", true)
                .setRetryCount(0)
                .setConnectTimeout(60000)
                .setCookieStore(new CookieManger(this));
         MMKV.initialize(this);

    }
}
