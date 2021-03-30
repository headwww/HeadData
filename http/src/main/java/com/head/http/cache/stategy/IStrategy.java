
package com.head.http.cache.stategy;

import com.head.http.cache.RxCache;
import com.head.http.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 *
 * 类名称：IStrategy.java <br/>
 * 类描述：实现缓存策略的接口，可以自定义缓存实现方式，只要实现该接口就可以了<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:30 <br/>
 * 
 * @version
 */
public interface IStrategy
{
    
    /**
     * 执行缓存
     *
     * @param rxCache 缓存管理对象
     * @param cacheKey 缓存key
     * @param cacheTime 缓存时间
     * @param source 网络请求对象
     * @param type 要转换的目标对象
     * @return 返回带缓存的Observable流对象
     */
    <T> Observable<CacheResult<T>> execute(RxCache rxCache, String cacheKey,
                                           long cacheTime, Observable<T> source, Type type);
    
}
