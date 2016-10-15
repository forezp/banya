package forezp.com.douyalibrary.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class WxPayUtils {
    public static int type;
    public static String order_id;
    private static String Key = "";

    public static final String partnerId ="";
    public static final String appId  ="";
    public static final String packageValue="Sign=WXPay";

    public static String createSign(String characterEncoding, SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Key);
        String sign = Md5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

   /** 返回一个定长的随机字符串(只包含大小写字母、数字)
    *
    * * @param length
    *         随机字符串长度
    * @return 随机字符串
    */
   public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
}
