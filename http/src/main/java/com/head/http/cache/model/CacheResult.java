package com.head.http.cache.model;

import java.io.Serializable;

/**
*
* 类名称：CacheResult.java <br/>
* 类描述：缓存对象<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:25 <br/>
* @version
*/
public class CacheResult<T> implements Serializable
{
    public boolean isFromCache;
    
    public T data;
    
    public CacheResult()
    {
    }
    
    public CacheResult(boolean isFromCache)
    {
        this.isFromCache = isFromCache;
    }
    
    public CacheResult(boolean isFromCache, T data)
    {
        this.isFromCache = isFromCache;
        this.data = data;
    }
    
    public boolean isCache()
    {
        return isFromCache;
    }
    
    public void setCache(boolean cache)
    {
        isFromCache = cache;
    }
    
    @Override
    public String toString()
    {
        return "CacheResult{" + "isCache=" + isFromCache + ", data=" + data
            + '}';
    }
}
