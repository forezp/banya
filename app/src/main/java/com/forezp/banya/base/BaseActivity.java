package com.forezp.banya.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.forezp.banya.R;
import com.forezp.banya.utils.ThemeUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import forezp.com.douyalibrary.utils.T;


/**
 * Created by b508a on 2016/1/25.
 */
public   abstract class BaseActivity extends  BaseFragmentActivity  implements IBaseView{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 弹出toast 显示时长short
     * @param pMsg
     */
    protected void toast(String pMsg) {
        T.show(this, pMsg, Toast.LENGTH_SHORT);
    }


    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void startThActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        startActivity(_Intent);
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }

    protected void startThActivityByIntent(Intent pIntent){
        startActivity(pIntent);
        overridePendingTransition(R.anim.trans_next_in, R.anim.trans_next_out);
    }


    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showTheToast(int resId) {

    }

    @Override
    public void showTheToast(String msg) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onServerFail(String msg) {

    }

    /**
     * 关闭界面
     **/
    public void backThActivity() {
        finish();
        overridePendingTransition(R.anim.trans_pre_in, R.anim.trans_pre_out);
    }

    protected void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

           // mTintManager.setStatusBarTintResource(R.color.red_base);//通知栏所需颜色
            mTintManager.setStatusBarTintColor(ThemeUtils.getThemeColor());
        }

    }

    protected void applyKitKatTranslucency(int res) {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            // mTintManager.setStatusBarTintResource(R.color.red_base);//通知栏所需颜色
            mTintManager.setStatusBarTintColor(res);
        }

    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

//    protected void setStatueBarTranslicent(){
//        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 19) {
//            // // 透明状态栏,透明是要加view.setPadding(0, MFSTool.dip2px(act, 22), 0, 0);
//            //getWindow().addFlags(
//            //WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //隐藏虚拟键盘,隐藏时需要加view.setPadding(0, 0, 0,MFSTool.dip2px(act, 24));
//            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            // view.setPadding(0, MFSTool.dip2px(act, 22), 0, 0);
//            // }
//            // if (MFSTool.checkDeviceHasNavigationBar(act))
//            // {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            //view.setPadding(0, 0, 0,
//            //				MFSTool.dip2px(act, 24));
//        }
//    }

}
