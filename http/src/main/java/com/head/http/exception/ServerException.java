
package com.head.http.exception;
/**
*
* 类名称：ServerException.java <br/>
* 类描述：处理服务器异常<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:40 <br/>
* @version
*/
public class ServerException extends RuntimeException
{
    private int errCode;
    
    private String message;
    
    public ServerException(int errCode, String msg)
    {
        super(msg);
        this.errCode = errCode;
        this.message = msg;
    }
    
    public int getErrCode()
    {
        return errCode;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}