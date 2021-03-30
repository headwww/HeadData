
package com.head.http.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.internal.$Gson$Types;
import com.head.http.cache.model.CacheResult;
import com.head.http.model.ApiResult;
import com.head.http.utils.Utils;

import okhttp3.ResponseBody;

/**
 * 类名称：CallBackProxy.java <br/>
 * 类描述：提供回调代理<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:32 <br/>
 */
public abstract class CallBackProxy<T extends ApiResult<R>, R>
    implements IType<T>
{
    CallBack<R> mCallBack;
    
    public CallBackProxy(CallBack<R> callBack)
    {
        mCallBack = callBack;
    }
    
    public CallBack getCallBack()
    {
        return mCallBack;
    }
    
    @Override
    public Type getType()
    {// CallBack代理方式，获取需要解析的Type
        Type typeArguments = null;
        if (mCallBack != null)
        {
            Type rawType = mCallBack.getRawType();// 如果用户的信息是返回List需单独处理
            if (List.class.isAssignableFrom(Utils.getClass(rawType, 0))
                || Map.class.isAssignableFrom(Utils.getClass(rawType, 0)))
            {
                typeArguments = mCallBack.getType();
            }
            else if (CacheResult.class
                .isAssignableFrom(Utils.getClass(rawType, 0)))
            {
                Type type = mCallBack.getType();
                typeArguments = Utils.getParameterizedType(type, 0);
            }
            else
            {
                Type type = mCallBack.getType();
                typeArguments = Utils.getClass(type, 0);
            }
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
