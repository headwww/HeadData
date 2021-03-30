
package com.head.http.callback;

import com.head.http.exception.ApiException;
import com.head.http.subsciber.IProgressDialog;
import com.head.http.subsciber.ProgressCancelListener;

import android.app.Dialog;
import android.content.DialogInterface;

import io.reactivex.disposables.Disposable;

/**
 *
 * 类名称：ProgressDialogCallBack.java <br/>
 * 类描述：可以自定义带有加载进度框的回调<br/>
 * 1.可以自定义带有加载进度框的回调,是否需要显示，是否可以取消<br>
 * 2.取消对话框会自动取消掉网络请求<br>
 * 创建人：shuwen <br/>
 * 创建时间：2020-01-08 14:37 <br/>
 * 
 * @version
 */
public abstract class ProgressDialogCallBack<T> extends CallBack<T>
    implements ProgressCancelListener
{
    private IProgressDialog progressDialog;
    
    private Dialog mDialog;
    
    private boolean isShowProgress = true;
    
    public ProgressDialogCallBack(IProgressDialog progressDialog)
    {
        this.progressDialog = progressDialog;
        init(false);
    }
    
    /**
     * 自定义加载进度框,可以设置是否显示弹出框，是否可以取消
     *
     * @param progressDialog dialog
     * @param isShowProgress 是否显示进度
     * @param isCancel 对话框是否可以取消
     */
    public ProgressDialogCallBack(IProgressDialog progressDialog,
        boolean isShowProgress, boolean isCancel)
    {
        this.progressDialog = progressDialog;
        this.isShowProgress = isShowProgress;
        init(isCancel);
    }
    
    /**
     * 初始化
     *
     * @param isCancel
     */
    private void init(boolean isCancel)
    {
        if (progressDialog == null)
            return;
        mDialog = progressDialog.getDialog();
        if (mDialog == null)
            return;
        mDialog.setCancelable(isCancel);
        if (isCancel)
        {
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override
                public void onCancel(DialogInterface dialogInterface)
                {
                    ProgressDialogCallBack.this.onCancelProgress();
                }
            });
        }
    }
    
    /**
     * 展示进度框
     */
    private void showProgress()
    {
        if (!isShowProgress)
        {
            return;
        }
        if (mDialog != null)
        {
            if (!mDialog.isShowing())
            {
                mDialog.show();
            }
        }
    }
    
    /**
     * 取消进度框
     */
    private void dismissProgress()
    {
        if (!isShowProgress)
        {
            return;
        }
        if (mDialog != null)
        {
            if (mDialog.isShowing())
            {
                mDialog.dismiss();
            }
        }
    }
    
    @Override
    public void onStart()
    {
        showProgress();
    }
    
    @Override
    public void onCompleted()
    {
        dismissProgress();
    }
    
    @Override
    public void onError(ApiException e)
    {
        dismissProgress();
    }
    
    @Override
    public void onCancelProgress()
    {
        if (disposed != null && !disposed.isDisposed())
        {
            disposed.dispose();
        }
    }
    
    private Disposable disposed;
    
    public void subscription(Disposable disposed)
    {
        this.disposed = disposed;
    }
}
