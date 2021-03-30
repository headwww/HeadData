
package com.head.http.func;

import com.head.http.cache.model.CacheResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
*
* 类名称：CacheResultFunc.java <br/>
* 类描述：缓存结果转换<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:40 <br/>
* @version
*/
public class CacheResultFunc<T> implements Function<CacheResult<T>, T>
{
    @Override
    public T apply(@NonNull CacheResult<T> tCacheResult)
        throws Exception
    {
        return tCacheResult.data;
    }
}
