package forezp.com.douyalibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class ViewUtils {
    /**
     * 打开软键盘
     */
    public static void openKeybord(View view, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     */
    public static void hideKeyboard( Context mContext,View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 根据屏幕宽度，获取imageView 的宽度
     *
     * @param context
     * @param paddingwidth imageview 在屏幕的paddingWidth
     * @param num          一行imageView 的个数
     */
    public static int getImageViewWidth(Context context, int paddingwidth, int num) {
        int screenWidth = ScreenUtils.getScreenWidthDp(context);
        return (screenWidth - paddingwidth) / num;
    }

    public static void setTextViewUnderline(TextView textView, String underlineString) {
        textView.setText(Html.fromHtml("<u>" + underlineString + "</u>"));
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    public static void hideKeyboard(Context context, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 获取是否存在NavigationBar：
    public static boolean checkDeviceHasNavigationBar(Context context)
    {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0)
        {

            hasNavigationBar = rs.getBoolean(id);
        }
        try
        {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride))
            {
                hasNavigationBar = false;
            }
            else if ("0".equals(navBarOverride))
            {
                hasNavigationBar = true;
            }
        }
        catch (Exception e)
        {

        }
        return hasNavigationBar;
    }



}
