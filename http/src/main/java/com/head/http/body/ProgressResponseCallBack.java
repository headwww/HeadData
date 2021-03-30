package com.head.http.body;

/**
 *
 * 类名称：ProgressResponseCallBack.java <br/>
 * 类描述：上传进度回调接口<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2019-10-24 09:16 <br/>
 * 
 * @version
 */
public interface ProgressResponseCallBack
{
    /**
     * 回调进度
     *
     * @param bytesWritten 当前读取响应体字节长度
     * @param contentLength 总长度
     * @param done 是否读取完成
     */
    void onResponseProgress(long bytesWritten, long contentLength,
                            boolean done);
}
