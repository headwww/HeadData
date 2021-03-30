
package com.head.http.subsciber;

import static com.head.http.utils.Utils.isNetworkAvailable;

import java.lang.ref.WeakReference;

import com.head.http.exception.ApiException;
import com.head.http.utils.HttpLog;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 *
 * 类名称：BaseSubscriber.java <br/>
 * 类描述：订阅的基类<br/>
 * 1.可以防止内存泄露。<br>
 * 2.在onStart()没有网络时直接onCompleted();<br>
 * 3.统一处理了异常<br>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-09 15:03 <br/>
 * 
 * @version
 */
public abstract class BaseSubscriber<T> extends DisposableObserver<T>
{
    public WeakReference<Context> contextWeakReference;

    public BaseSubscriber()
    {
    }

    @Override
    protected void onStart()
    {
        HttpLog.i("-->http is onStart");
        if (contextWeakReference != null && contextWeakReference.get() != null
            && !isNetworkAvailable(contextWeakReference.get()))
        {
             Toast.makeText(contextWeakReference.get(), "网络未连接", Toast.LENGTH_SHORT).show();
            onComplete();
        }
    }

    public BaseSubscriber(Context context)
    {
        if (context != null)
        {
            contextWeakReference = new WeakReference<>(context);
        }
    }
    
    @Override
    public void onNext(@NonNull T t)
    {
        HttpLog.i("-->http is onNext");
    }
    
    @Override
    public final void onError(Throwable e)
    {
        HttpLog.e("-->http is onError" + e);
        if (e instanceof ApiException)
        {
            HttpLog.e("--> e instanceof ApiException err:" + e);
            onError((ApiException)e);
        }
        else
        {
            HttpLog.e("--> e !instanceof ApiException err:" + e);
            onError(ApiException.handleException(e));
        }
    }
    
    @Override
    public void onComplete()
    {
        HttpLog.i("-->http is onComplete");
    }
    
    public abstract void onError(ApiException e);
    
}
