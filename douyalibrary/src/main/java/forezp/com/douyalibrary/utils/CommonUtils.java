package forezp.com.douyalibrary.utils;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**获取系统版本、获取音频时间、从uri获取path
 * Created by b508a on 2015/12/28.
 */
public class CommonUtils {


    public static String getOsVersion(){

        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取UMmeng渠道名
     * @param ctx
     * @return
     */
    public static String getChannelName(Context ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {

                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL");
                        if(TextUtils.isEmpty(channelName)) {//360纯数字 会被当做int
                            channelName = applicationInfo.metaData.getInt("UMENG_CHANNEL") + "";
                        }
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }


    /**
     * <获取本软件的版本>
     */
    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = null;

        int versionCode = 0;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName().toString(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != packageInfo) {
            versionCode = packageInfo.versionCode;
        }
        return versionCode;
    }

    /**
     * <获取本软件的版本>
     *
     *
     *
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = null;
        String versionName = "1.0";
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName().toString(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != packageInfo) {
            versionName = packageInfo.versionName;
        }
        return versionName;
    }



    /**
     *  从uri获取path，主要用于图片选择返回
     * @param context
     * @param contentUri
     * @return
     */
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 获取文件播放时间
     * 00:00:20
     * @param file
     * @return
     */
    public static String getMusicTime(Context context, File file){
        java.util.Date date;
        SimpleDateFormat sy1;
        String dateFormat = "error";

        try {
            sy1 = new SimpleDateFormat("HH:mm:ss");//设置为时分秒的格式

            //使用媒体库获取播放时间
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(context, Uri.parse(file.toString()));

            //使用Date格式化播放时间mediaPlayer.getDuration()
            date = sy1.parse("00:00:00");
            date.setTime(mediaPlayer.getDuration() + date.getTime());//用消除date.getTime()时区差
            dateFormat = sy1.format(date);

            mediaPlayer.release();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat;
    }
    /**
     * 获取音频文件的时间 毫秒
     * @param file
     * @return int sencond
     */
    public static  int getAudioTime(Context context, File file){

        int time = 0;
        try {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(context, Uri.parse(file.toString()));
            time=mediaPlayer.getDuration();
            mediaPlayer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getDeviceId(Context context){
        TelephonyManager telephonyManager =(TelephonyManager)context.getSystemService( Context.TELEPHONY_SERVICE );
        return telephonyManager.getDeviceId();
    }

}
