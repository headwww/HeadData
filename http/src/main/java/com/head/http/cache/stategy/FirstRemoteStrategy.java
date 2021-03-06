
package com.head.http.cache.stategy;

import java.lang.reflect.Type;
import java.util.Arrays;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import io.reactivex.Observable;

/**
*
* 类名称：FirstRemoteStrategy.java <br/>
* 类描述：描述：先请求网络，网络请求失败，再加载缓存<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:29 <br/>
* @version
*/
public final class FirstRemoteStrategy extends BaseStrategy
{
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key,
                                                  long time, Observable<T> source, Type type)
    {
        Observable<CacheResult<T>> cache =
            loadCache(rxCache, type, key, time, true);
        Observable<CacheResult<T>> remote =
            loadRemote(rxCache, key, source, false);
        // return remote.switchIfEmpty(cache);
        return Observable.concatDelayError(Arrays.asList(remote, cache))
            .take(1);
    }
}
