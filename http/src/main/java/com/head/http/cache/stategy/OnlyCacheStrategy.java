
package com.head.http.cache.stategy;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;
/**
*
* 类名称：OnlyCacheStrategy.java <br/>
* 类描述：只读缓存<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:31 <br/>
* @version
*/
public final class OnlyCacheStrategy extends BaseStrategy
{
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key,
                                                  long time, Observable<T> source, Type type)
    {
        return loadCache(rxCache, type, key, time, false);
    }
}
