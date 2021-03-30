
package com.head.http.cache.core;

import java.lang.reflect.Type;


/**
*
* 类名称：MemoryCache.java <br/>
* 类描述：内存缓存<br/>
* 创建人：shuwen <br/>
* 创建时间：2019-10-24 09:29 <br/>
* @version
*/
public class MemoryCache extends BaseCache
{
    @Override
    protected boolean doContainsKey(String key)
    {
        return false;
    }
    
    @Override
    protected boolean isExpiry(String key, long existTime)
    {
        return false;
    }
    
    @Override
    protected <T> T doLoad(Type type, String key)
    {
        return null;
    }
    
    @Override
    protected <T> boolean doSave(String key, T value)
    {
        return false;
    }
    
    @Override
    protected boolean doRemove(String key)
    {
        return false;
    }
    
    @Override
    protected boolean doClear()
    {
        return false;
    }
}
