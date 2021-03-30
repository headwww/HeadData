
package com.head.http.callback;

/**
*
* 类名称：DownloadProgressCallBack.java <br/>
* 类描述：下载进度回调（主线程，可以直接操作UI）<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:34 <br/>
* @version
*/
public abstract class DownloadProgressCallBack<T> extends CallBack<T>
{
    public DownloadProgressCallBack()
    {
    }
    
    @Override
    public void onSuccess(T response)
    {
        
    }
    
    public abstract void update(long bytesRead, long contentLength,
        boolean done);
    
    public abstract void onComplete(String path);
    
    @Override
    public void onCompleted()
    {
        
    }
}
