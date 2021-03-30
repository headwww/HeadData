
package com.head.http.func;

import com.head.http.exception.ApiException;
import com.head.http.exception.ServerException;
import com.head.http.model.ApiResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
*
* 类名称：HandleFuc.java <br/>
* 类描述：ApiResult<T>转换T<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:42 <br/>
* @version
*/
public class HandleFuc<T> implements Function<ApiResult<T>, T>
{
    @Override
    public T apply(@NonNull ApiResult<T> tApiResult)
        throws Exception
    {
        if (ApiException.isOk(tApiResult))
        {
            return tApiResult.getData();// == null ?
                                        // Optional.ofNullable(tApiResult.getData()).orElse(null)
                                        // : tApiResult.getData();
        }
        else
        {
            throw new ServerException(tApiResult.getCode(),
                tApiResult.getMsg());
        }
    }
}
