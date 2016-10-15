package forezp.com.douyalibrary.utils;


import android.content.Context;
import android.content.SharedPreferences;


/**
 * sharedPreference 工具类
 * Created by b508a on 2015/12/31.
 */
public class SPUtils {

    /**
     * 获取首选项
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences("shudong", Context.MODE_PRIVATE);
    }
    /**
     * 向首选项中存取数据(仅限于String,Integer,Boolean)
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value){
        SharedPreferences sp = getSharedPreference(context);
        SharedPreferences.Editor editor = sp.edit();
        if(value instanceof String){
            editor.putString(key, (String) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }
        editor.commit();
    }
    /**
     * 获取String
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key){
        SharedPreferences sp = getSharedPreference(context);
        return sp.getString(key, null);
    }
    /**
     * 获取int
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key){
        SharedPreferences sp = getSharedPreference(context);
        return sp.getInt(key, 0);
    }
    /**
     * 获取int(可以控制默认值)
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue){
        SharedPreferences sp = getSharedPreference(context);
        return sp.getInt(key, defaultValue);
    }
    /**
     * 获取Boolean
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key){
        SharedPreferences sp = getSharedPreference(context);
        return sp.getBoolean(key, false);
    }

}

