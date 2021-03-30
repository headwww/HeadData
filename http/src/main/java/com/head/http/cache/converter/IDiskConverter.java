
package com.head.http.cache.converter;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 *
 * 类名称：IDiskConverter.java <br/>
 * 类描述：通用转换器接口<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2019-10-24 09:24 <br/>
 * 
 * @version
 */
public interface IDiskConverter
{
    
    /**
     * 读取
     *
     * @param source 输入流
     * @param type 读取数据后要转换的数据类型 这里没有用泛型T或者Tyepe来做，是因为本框架决定的一些问题，泛型会丢失
     * @return
     */
    <T> T load(InputStream source, Type type);
    
    /**
     * 写入
     *
     * @param sink
     * @param data 保存的数据
     * @return
     */
    boolean writer(OutputStream sink, Object data);
    
}
