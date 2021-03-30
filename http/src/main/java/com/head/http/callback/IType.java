
package com.head.http.callback;

import java.lang.reflect.Type;

/**
*
* 类名称：IType.java <br/>
* 类描述：获取类型接口<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:34 <br/>
* @version
*/
public interface IType<T>
{
    Type getType();
}
