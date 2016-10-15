package forezp.com.douyalibrary.utils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/26.
 */
public class MemCacheHelper {
    private static final MemCacheHelper single = new MemCacheHelper();
    /**
     * 全局通用的临时缓存变量
     * 使用方式，key是包名+变量名，value是引用类型，值类型请包装比如int-》Integer
     */
    public HashMap<String, Object> mTempVariable = new HashMap<>();

    public static MemCacheHelper getInstance() {
        return single;
    }

    /**
     * 存储一个临时变量
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        mTempVariable.put(key, value);
    }

    /**
     * 获取一个临时变量
     *
     * @param key
     * @return
     */
    public <T> T get(String key) {
        return (T) mTempVariable.get(key);
    }

    /**
     * 删除临时变量
     *
     * @param key
     */
    public void remove(String key) {
        mTempVariable.remove(key);
    }
}
