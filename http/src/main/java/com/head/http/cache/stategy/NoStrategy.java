
package com.head.http.cache.stategy;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 类名称：NoStrategy.java <br/>
 * 类描述：网络加载，不缓存<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:31 <br/>
 */
public class NoStrategy implements IStrategy
{
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache,
                                                  String cacheKey, long cacheTime, Observable<T> source, Type type)
    {
        return source.map(new Function<T, CacheResult<T>>()
        {
            @Override
            public CacheResult<T> apply(@NonNull T t)
                throws Exception
            {
                return new CacheResult<T>(false, t);
            }
        });
    }
}
