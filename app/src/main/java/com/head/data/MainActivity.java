package com.head.data;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.head.http.HeadHttp;
import com.head.http.callback.SimpleCallBack;
import com.head.http.cookie.CookieManger;
import com.head.http.exception.ApiException;
import com.tencent.mmkv.MMKV;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeadHttp
                        .post("http://192.168.1.244:9094/api/logisticsCtrl/CurrentStoreAndCheck")
                        .upJson("")

                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {
                                Log.e("Err=====", e.getMessage()+e.getCode());

                            }

                            @Override
                            public void onSuccess(String s) {
                                // AuthorityParentList sss=new AuthorityParentList();
                                // sss.setName("--qqqq-");
                                // JsonMap userJson = JsonBean.setBean(sss);

                                // JsonList list=JsonList.parse(s);
                                // Log.e("=====",""+list.getJsonMap(0).getString("test"));
                            }
                        });
            }
        });

        MMKV kv = MMKV.defaultMMKV();

        kv.encode("bool", true);
        boolean bValue = kv.decodeBool("bool");

        kv.encode("int", Integer.MIN_VALUE);
        int iValue = kv.decodeInt("int");

        kv.encode("string", "Hello from mmkv");
        String str = kv.decodeString("string");

        Log.e("====", bValue + "" + iValue + str + "--");
        CookieManger cookieManger = new CookieManger(this);
        cookieManger.removeAll();

        disposable = HeadHttp
                .post("http://192.168.1.244:9094/api/login")
                .params("username", "cs001")
                .params("password", "123456")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String o) {
                        HeadHttp
                                .post("http://192.168.1.244:9094/api/logisticsCtrl/CurrentStoreAndCheck")
                                .upJson("")

                                .execute(new SimpleCallBack<String>() {
                                    @Override
                                    public void onError(ApiException e) {

                                    }

                                    @Override
                                    public void onSuccess(String s) {
                                        // AuthorityParentList sss=new AuthorityParentList();
                                        // sss.setName("--qqqq-");
                                        // JsonMap userJson = JsonBean.setBean(sss);

                                        // JsonList list=JsonList.parse(s);
                                        // Log.e("=====",""+list.getJsonMap(0).getString("test"));
                                    }
                                });
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // disposable.dispose();
    }
}
