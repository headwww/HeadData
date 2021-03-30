
package com.head.http.body;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
/**
*
* 类名称：RequestBodyUtils.java <br/>
* 类描述：请求体处理工具类<br/>
* 创建人：shuwen <br/>
* 创建时间：2019-10-24 09:17 <br/>
* @version
*/
public class RequestBodyUtils
{
    
    // public final static MediaType MEDIA_TYPE_MARKDOWN =
        // MediaType.parse("text/x-markdown; charset=utf-8");
    
    public static RequestBody create(final MediaType mediaType,
        final InputStream inputStream)
    {
        return new RequestBody()
        {
            @Override
            public MediaType contentType()
            {
                return mediaType;
            }
            
            @Override
            public long contentLength()
            {
                try
                {
                    return inputStream.available();
                }
                catch (IOException e)
                {
                    return 0;
                }
            }
            
            @Override
            public void writeTo(BufferedSink sink)
                throws IOException
            {
                Source source = null;
                try
                {
                    source = Okio.source(inputStream);
                    sink.writeAll(source);
                }
                finally
                {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
