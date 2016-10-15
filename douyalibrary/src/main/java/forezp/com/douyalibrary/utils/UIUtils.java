package forezp.com.douyalibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/5/26.
 */
public class UIUtils {


    /**
     * 截图
     *
     * @param activity
     * @return
     */
    public static Bitmap captureContent(Activity activity) {
        //View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap b1 = view.getDrawingCache();
        //Bitmap b1=loadBitmapFromView(view,true,width,height);
        // 获取状态栏高度 /
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 获取屏幕长和高

        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }
    public static Bitmap loadBitmapFromView(View v, boolean isParemt,int wid,int hei) {
        if (v == null) {
            return null;
        }

        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(wid,hei, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        v.draw(c);
        return screenshot;
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */

    public static Bitmap captureScreen(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    public static void forceImmersiveWindow(Activity context) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        context.getWindow().setAttributes(params);
    }

    /**
     * 沉浸式窗体，隐藏status bar和 navigation bar
     * 4.4以下不隐藏导航栏，因为点击事件会呼唤出导航栏，导致点击事件和预想的不一样
     *
     * @param context
     */
    public static void immersiveWindow(Activity context) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        } else {
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        context.getWindow().setAttributes(params);
    }


    public static void popWindow(Context applicationContext, int layoutResId) {
        WindowManager wm = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams para = new WindowManager.LayoutParams();
        para.height = ViewGroup.LayoutParams.MATCH_PARENT;
        para.width = ViewGroup.LayoutParams.MATCH_PARENT;
        para.format = 1;

        para.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        para.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        View mView = LayoutInflater.from(applicationContext).inflate(
                layoutResId, null);
        wm.addView(mView, para);
    }
}
