
package com.head.http.exception;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.head.http.model.ApiResult;

import android.net.ParseException;
import android.util.Log;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 *
 * 类名称：ApiException.java <br/>
 * 类描述：统一处理了API异常错误<br/>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:40 <br/>
 * 
 * @version
 */
@SuppressWarnings("deprecation")
public class ApiException extends Exception
{
    // 对应HTTP的状态码
    private static final int BADREQUEST = 400;
    
    private static final int UNAUTHORIZED = 401;
    
    private static final int FORBIDDEN = 403;
    
    private static final int NOT_FOUND = 404;
    
    private static final int METHOD_NOT_ALLOWED = 405;
    
    private static final int REQUEST_TIMEOUT = 408;
    
    private static final int INTERNAL_SERVER_ERROR = 500;
    
    private static final int BAD_GATEWAY = 502;
    
    private static final int SERVICE_UNAVAILABLE = 503;
    
    private static final int GATEWAY_TIMEOUT = 504;
    
    private static final int CUSTOM_CODE = 666;
    
    private final int code;
    
    private String displayMessage;
    
    public static final int UNKNOWN = 1000;
    
    public static final int PARSE_ERROR = 1001;
    
    private String message;

    private String errorText;

    private String throwable;
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public ApiException(Throwable throwable, int code)
    {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

    public int getCode()
    {
        return code;
    }
    
    public String getDisplayMessage()
    {
        return displayMessage;
    }
    
    public void setDisplayMessage(String msg)
    {
        this.displayMessage = msg + "(code:" + code + ")";
    }
    
    public static boolean isOk(ApiResult apiResult)
    {
        if (apiResult == null)
            return false;
        if (apiResult.isOk() /* || ignoreSomeIssue(apiResult.getCode()) */)
            return true;
        else
            return false;
    }
    
    public static ApiException handleException(Throwable e)
    {
        ApiException ex;
        if (e instanceof HttpException)
        {
            HttpException httpException = (HttpException)e;
            ex = new ApiException(httpException, httpException.code());
            /*
             * switch (httpException.code()) { case BADREQUEST: case
             * UNAUTHORIZED: case FORBIDDEN: case NOT_FOUND: case
             * REQUEST_TIMEOUT: case GATEWAY_TIMEOUT: case
             * INTERNAL_SERVER_ERROR: case BAD_GATEWAY: case
             * SERVICE_UNAVAILABLE: default: ex.message =
             * "网络错误,Code:"+httpException.code()+" ,err:"+httpException.
             * getMessage(); break; }
             */
            try {
                Response<?> response = httpException.response();
                if (response != null && response.errorBody() != null) {
                    String errorBody = response.errorBody().string();

                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(errorBody, ErrorResponse.class);
                        if (errorResponse != null) {
                            ex.message = errorResponse.getErrorText();
                            ex.throwable = errorResponse.getThrowable();
                        }
                    } catch (JsonSyntaxException jsonEx) {
                        ex.message = errorBody;
                    }
                }
            } catch (IOException ioException) {
                ex.message = "读取服务器错误信息失败：" + ioException.getMessage();
            }
//            ex.message = httpException.getMessage();
            return ex;
        }
        else if (e instanceof ServerException)
        {
            ServerException resultException = (ServerException)e;
            ex = new ApiException(resultException,
                resultException.getErrCode());
            ex.message = resultException.getMessage();
            return ex;
        }
        else if (e instanceof JsonParseException || e instanceof JSONException
            || e instanceof JsonSyntaxException || e instanceof JsonSerializer
            || e instanceof NotSerializableException
            || e instanceof ParseException)
        {
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        }
        else if (e instanceof ClassCastException)
        {
            ex = new ApiException(e, ERROR.CAST_ERROR);
            ex.message = "类型转换错误";
            return ex;
        }
        else if (e instanceof ConnectException)
        {
            ex = new ApiException(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        }
        else if (e instanceof javax.net.ssl.SSLHandshakeException)
        {
            ex = new ApiException(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            
            return ex;
        }
        else if (e instanceof ConnectTimeoutException)
        {
            ex = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        }
        else if (e instanceof java.net.SocketTimeoutException)
        {
            ex = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        }
        else if (e instanceof UnknownHostException)
        {
            ex = new ApiException(e, ERROR.UNKNOWNHOST_ERROR);
            ex.message = "无法解析该域名";
            return ex;
        }
        else if (e instanceof NullPointerException)
        {
            ex = new ApiException(e, ERROR.NULLPOINTER_EXCEPTION);
            ex.message = "NullPointerException";
            return ex;
        }
        else
        {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
    
    /*
     * public String getErrMessage() { return message; }
     */
    
    /**
     * 约定异常
     */
    public static class ERROR
    {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = UNKNOWN + 1;
        
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = PARSE_ERROR + 1;
        
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = NETWORD_ERROR + 1;
        
        /**
         * 证书出错
         */
        public static final int SSL_ERROR = HTTP_ERROR + 1;
        
        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = SSL_ERROR + 1;
        
        /**
         * 调用错误
         */
        public static final int INVOKE_ERROR = TIMEOUT_ERROR + 1;
        
        /**
         * 类转换错误
         */
        public static final int CAST_ERROR = INVOKE_ERROR + 1;
        
        /**
         * 请求取消
         */
        public static final int REQUEST_CANCEL = CAST_ERROR + 1;
        
        /**
         * 未知主机错误
         */
        public static final int UNKNOWNHOST_ERROR = REQUEST_CANCEL + 1;
        
        /**
         * 空指针错误
         */
        public static final int NULLPOINTER_EXCEPTION = UNKNOWNHOST_ERROR + 1;
    }
}