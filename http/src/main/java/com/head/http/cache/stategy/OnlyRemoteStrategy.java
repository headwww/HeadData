
package com.head.http.cache.stategy;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 *
 * 类名称：OnlyRemoteStrategy.java <br/>
 * 类描述：只请求网络<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:31 <br/>
 * 
 * @version
 */
public final class OnlyRemoteStrategy extends BaseStrategy
{
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key,
                                                  long time, Observable<T> source, Type type)
    {
        return loadRemote(rxCache, key, source, false);
    }
}
