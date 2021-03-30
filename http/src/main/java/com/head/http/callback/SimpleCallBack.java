
package com.head.http.callback;

/**
 *
 * 类名称：SimpleCallBack.java <br/>
 * 类描述：简单的回调,默认可以使用该回调，不用关注其他回调方法<br/>
 * 使用该回调默认只需要处理onError，onSuccess两个方法既成功失败<br>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:38 <br/>
 * 
 * @version
 */
public abstract class SimpleCallBack<T> extends CallBack<T>
{
    
    @Override
    public void onStart()
    {
    }
    
    @Override
    public void onCompleted()
    {
        
    }
}
