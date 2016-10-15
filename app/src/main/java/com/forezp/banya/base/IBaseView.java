package com.forezp.banya.base;

import android.content.Context;

/**
 * 公共View接口
 *
 * @author Ht
 */
public interface IBaseView {


    /**
     * 显示可取消的进度条
     *
     * @param message 要显示的信息
     */
    void showProgress(String message);

    /**
     * 显示可取消的无文字进度条
     */
    void showProgress();

    /**
     * 取消显示进度条
     */
    void cancelProgress();

    /**
     * 根据资源文件id弹出toast
     *
     * @param resId 资源文件id
     */
    void showTheToast(int resId);

    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showTheToast(String msg);

    /**
     * 获取当前上下文对象
     * @return
     */
    Context getContext();

     void onServerFail(String msg);
}
