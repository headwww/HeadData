
package com.head.http.cookie;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
*
* 类名称：CookieManger.java <br/>
* 类描述：cookie管理器<br/>
* 创建人：shuwen <br/>
* 创建时间：2020-01-08 14:39 <br/>
* @version
*/
public class CookieManger implements CookieJar
{
    
    private static Context mContext;
    
    private static PersistentCookieStore cookieStore;
    
    public CookieManger(Context context)
    {
        mContext = context;
        if (cookieStore == null)
        {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }
    
    public void addCookies(List<Cookie> cookies)
    {
        cookieStore.addCookies(cookies);
    }
    
    public void saveFromResponse(HttpUrl url, Cookie cookie)
    {
        if (cookie != null)
        {
            cookieStore.add(url, cookie);
        }
    }
    
    public PersistentCookieStore getCookieStore()
    {
        return cookieStore;
    }
    
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        if (cookies != null && cookies.size() > 0)
        {
            for (Cookie item : cookies)
            {
                cookieStore.add(url, item);
            }
        }
    }
    
    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
    
    public void remove(HttpUrl url, Cookie cookie)
    {
        cookieStore.remove(url, cookie);
    }
    
    public void removeAll()
    {
        cookieStore.removeAll();
    }
    
}
