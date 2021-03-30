
package com.head.http.func;

import com.head.http.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 *
 * 类名称：HttpResponseFunc.java <br/>
 * 类描述：异常转换处理<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:42 <br/>
 * 
 * @version
 */
public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>>
{
    @Override
    public Observable<T> apply(@NonNull Throwable throwable)
        throws Exception
    {
        return Observable.error(ApiException.handleException(throwable));
    }
}
