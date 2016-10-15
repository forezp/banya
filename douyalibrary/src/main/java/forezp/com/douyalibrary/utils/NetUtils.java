package forezp.com.douyalibrary.utils;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络是否可用、网络种类
 * 联网工具类
 * Created by b508a on 2015/12/28.
 */
public class NetUtils {

    /**
     * 检查是否有网络
     * @param context
     * @return
     */
    public static boolean checkNetWorkIsAvailable(Context context) {
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            return true;
        else
            return false;
    }


    /**
     * Cmwap网络是否已连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnectedByCmwap(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo != null && networkInfo.isConnected()
                && networkInfo.getExtraInfo() != null
                && networkInfo.getExtraInfo().toLowerCase().contains("cmwap");
    }

    /**
     * 连接的是否是2G网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnectedBy2G(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isConnected()) {
            int subtype = networkInfo.getSubtype();
            if (subtype == TelephonyManager.NETWORK_TYPE_GPRS
                    || subtype == TelephonyManager.NETWORK_TYPE_EDGE
                    || subtype == TelephonyManager.NETWORK_TYPE_CDMA) {// 移动和联通2G
                return true;
            }
        }
        return false;
    }

    /**
     * 连接的是否是3G网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnectedBy3G(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isConnected()) {
            int subtype = networkInfo.getSubtype();
            if (subtype == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subtype == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subtype == TelephonyManager.NETWORK_TYPE_UMTS
                    || subtype == TelephonyManager.NETWORK_TYPE_HSPA) {// 电信或联通3G
                return true;
            }
        }
        return false;
    }

    /**
     * Wifi网络是否已连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnectedByWifi(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 网络是否已连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}

