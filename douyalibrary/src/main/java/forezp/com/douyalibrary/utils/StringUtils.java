package forezp.com.douyalibrary.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class StringUtils {

//    /**
//     * 搜索的时候根据关键字对内容设置红色
//     *
//     * @param context
//     * @param content
//     * @param keyword
//     * @return
//     */
//
//    public static SpannableStringBuilder setContentRedColorWithKeyword(Context context, String content, String keyword) {
//        ForegroundColorSpan span = new ForegroundColorSpan(context.getResources()
//                .getColor(R.color.red));// 要显示的颜色
//        SpannableStringBuilder builder = new SpannableStringBuilder(content);
//        if (TextUtils.isEmpty(keyword)) {
//            return builder;
//        }
//        int input = isChinese(keyword);
//        int end = isChinese(content);
//        StringBuilder sb = null;
//        StringBuilder sb2 = null;
//        ArrayList<String> ss = new ArrayList<String>();
//        if (input == 0 && (end == 1 || end == 2))// 如果输入的是英文,查询结果为中文
//        {
//            sb = new StringBuilder();
//            sb2 = new StringBuilder();
//            // 将结果由汉字转成拼音
//            for (int i = 0; i < content.length(); i++) {
//                String s = content.substring(i, i + 1);
//                LogCat.lztlog(s);
//                String py = ChineseSpelling.getPinYin(s).charAt(0) + "";
//                String py2 = ChineseSpelling.getPinYin(s);
//                LogCat.lztlog(py);
//                sb.append(py);
//                sb2.append(py2);
//            }
//            String pyFristEnd = sb.toString();
//            for (int i = 0; i < pyFristEnd.length(); i++) {
//                String s = pyFristEnd.charAt(i) + "";
//
//                if (keyword.contains(s)) {
//                    int indexOf = pyFristEnd.indexOf(s);
//                    if (indexOf != -1) {
//                        builder.setSpan(span, indexOf, indexOf + 1,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                }
//
//                if (i != pyFristEnd.length() - 1) {
//                    String s2 = pyFristEnd.charAt(i + 1) + "";
//                    String sss = s + s2;
//                    if (keyword.contains(sss)) {
//                        int indexOf = pyFristEnd.indexOf(sss);
//                        if (indexOf != -1) {
//                            LogCat.lztlog("进来了!!!!!!!!!!");
//                            builder.setSpan(span, indexOf,
//                                    indexOf + sss.length(),
//                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                            break;
//                        }
//                    }
//                }
//            }
//            if (end == 2) {
//                keyword = keyword.toLowerCase();
//                content = content.toLowerCase();
//                int index = content.indexOf(keyword);// 从第几个匹配上
//                int indexOf = content.indexOf(keyword.charAt(0));
//                if (index != -1) {// 有这个关键字用builder显示
//                    builder.setSpan(span, indexOf, indexOf + keyword.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//        } else {
//            keyword = keyword.toLowerCase();
//            content = content.toLowerCase();
//            int index = content.indexOf(keyword);// 从第几个匹配上
//            int indexOf = content.indexOf(keyword.charAt(0));
//            if (index != -1) {// 有这个关键字用builder显示
//                builder.setSpan(span, indexOf, indexOf + keyword.length(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }
//        if (sb != null && keyword.equals(sb.toString())) {
//            builder.setSpan(span, 0, sb.toString().length(),
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        if ((sb2 != null && keyword.equals(sb2.toString()))) {
//            builder.setSpan(span, 0, content.length(),
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//
//        return builder;
//    }
//

    /**
     * 判断是否全为汉字或者字母
     *
     * @param str
     * @return 0全为字母, 1全为汉字, 2汉字字母都有;
     */
    public static int isChinese(String str) {
        int s = 0;
        int s1 = 0;
        for (int i = 0; i < str.length(); i++) {
            String c = str.charAt(i) + "";
            try {
                byte[] bytes = c.getBytes("gb2312");// gb2312编码 汉字2个字符,英文1个字符
                if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
                    // log
                    throw new RuntimeException("illegal resource string");
                    // System.out.println("error");
                }
                if (bytes.length == 1) { // 英文字符
                    s = 1;
                }
                if (bytes.length == 2) { // 中文字符
                    s1 = 1;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (s == 1 && s1 == 0)// 只有英文字符
        {
            return 0;
        } else if (s == 0 && s1 == 1)// 只有中文字符
        {
            return 1;
        } else {// 都有
            return 2;
        }
    }


    /**
     * 数值转换 如超过9999 显示为1.0万
     */
    public static String numConvert(String num) {
        int number = Integer.valueOf(num);

        if (number > 10000) {
            int s1 = number / 10000;
            int s2 = number % 10000 / 1000;
            return "" + s1 + "." + s2 + "万";
        } else {
            return num;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> DATE_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return DATE_FORMATER.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param dateline
     * @return
     */
    public static String friendlyTime(String dateline) {
        if(TextUtils.isEmpty(dateline)) return "";
        Date time = new Date();
        long timeStamp = Long.parseLong(dateline);
        time.setTime(timeStamp * 1000);

        String ftime = "";
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                long minue = (cal.getTimeInMillis() - time.getTime()) / 60000;
                if (minue == 0) {
                    ftime = "现在";
                } else if (minue > 0) {
                    ftime = minue + "分钟前";
                } else if (minue < 0) {
                    ftime = Math.abs(minue) + "分钟后";
                }
            } else if (hour > 0) {
                ftime = hour + "小时前";
            } else if (hour < 0) {
                ftime = Math.abs(hour) + "小时后";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);

        int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
        if (hour == 0) {
            long minue = (cal.getTimeInMillis() - time.getTime()) / 60000;
            if (minue == 0) {
                ftime = "现在";
            } else if (minue > 0) {
                ftime = minue + "分钟前";
            } else if (minue < 0) {
                ftime = Math.abs(minue) + "分钟后";
            }
        } else if (hour > 0) {
            ftime = hour + "小时前";
        } else if (hour < 0) {
            ftime = Math.abs(hour) + "小时后";
        }

        if (days == 1 && hour > 23) {
            ftime = "昨天";
        } else if (days == -1 && hour < -23) {
            ftime = "明天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days == -2) {
            ftime = "后天";
        } else if(days > 2 || days < -2) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    public static String getTimeConsuming(String startTime, String endTime) {
        if(TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) return "";

        long start = Long.parseLong(startTime);
        long end = Long.parseLong(endTime);

        long consum = end - start;

        return String.valueOf(consum / 60);
    }


}
