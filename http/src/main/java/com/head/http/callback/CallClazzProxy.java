
package com.head.http.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.internal.$Gson$Types;
import com.head.http.model.ApiResult;
import com.head.http.utils.Utils;

import okhttp3.ResponseBody;

/**
 *
 * 类名称：CallClazzProxy.java <br/>
 * 类描述：提供Clazz回调代理<br/>
 * 主要用于可以自定义ApiResult 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:32 <br/>
 * 
 * @version
 */
public abstract class CallClazzProxy<T extends ApiResult<R>, R>
    implements IType<T>
{
    private Type type;
    
    public CallClazzProxy(Type type)
    {
        this.type = type;
    }
    
    public Type getCallType()
    {
        return type;
    }
    
    @Override
    public Type getType()
    {// CallClazz代理方式，获取需要解析的Type
        Type typeArguments = null;
        if (type != null)
        {
            typeArguments = type;
        }
        if (typeArguments == null)
        {
            typeArguments = ResponseBody.class;
        }
        Type rawType = Utils.findNeedType(getClass());
        if (rawType instanceof ParameterizedType)
        {
            rawType = ((ParameterizedType)rawType).getRawType();
        }
        return $Gson$Types
            .newParameterizedTypeWithOwner(null, rawType, typeArguments);
    }
}
