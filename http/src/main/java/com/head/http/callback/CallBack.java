
package com.head.http.callback;

import com.head.http.exception.ApiException;
import com.head.http.utils.Utils;

import java.lang.reflect.Type;

/**
 *
 * 类名称：CallBack.java <br/>
 * 类描述：网络请求回调<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:32 <br/>
 * 
 * @version
 */
public abstract class CallBack<T> implements IType<T>
{
    public abstract void onStart();
    
    public abstract void onCompleted();
    
    public abstract void onError(ApiException e);
    
    public abstract void onSuccess(T t);
    
    @Override
    public Type getType()
    {// 获取需要解析的泛型T类型
        return Utils.findNeedClass(getClass());
    }
    
    public Type getRawType()
    {// 获取需要解析的泛型T raw类型
        return Utils.findRawType(getClass());
    }
}
