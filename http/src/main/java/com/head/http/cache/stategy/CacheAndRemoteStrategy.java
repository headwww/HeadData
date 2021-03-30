
package com.head.http.cache.stategy;

import java.lang.reflect.Type;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 *
 * 类名称：CacheAndRemoteStrategy.java <br/>
 * 类描述：先显示缓存，再请求网络<br/>
 * <-------此类加载用的是反射 所以类名是灰色的 没有直接引用 不要误删----------------><br>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:26 <br/>
 * 
 * @version
 */
public final class CacheAndRemoteStrategy extends BaseStrategy
{
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key,
                                                  long time, Observable<T> source, Type type)
    {
        Observable<CacheResult<T>> cache =
            loadCache(rxCache, type, key, time, true);
        Observable<CacheResult<T>> remote =
            loadRemote(rxCache, key, source, false);
        return Observable.concat(cache, remote)
            .filter(new Predicate<CacheResult<T>>()
            {
                @Override
                public boolean test(@NonNull CacheResult<T> tCacheResult)
                    throws Exception
                {
                    return tCacheResult != null && tCacheResult.data != null;
                }
            });
    }
    
}
