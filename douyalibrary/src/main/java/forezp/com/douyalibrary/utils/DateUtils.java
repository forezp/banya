package forezp.com.douyalibrary.utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间操作类、各种时间转换
 * Created by b508a on 2015/12/28.
 */
public class DateUtils {

    /**
     * 字符串转换成date 对象
     * @param dateString
     * @return
     */
    public static Date Convert2Date(String dateString)
    {
        return Convert2Date(dateString,"yyyy-MM-dd");
    }

    /**
     * 字符串 转date
     * @param dateString
     * @param type
     * @return
     */
    public static Date Convert2Date(String dateString, String type) {
        if (dateString == null || dateString.trim().equals("") || dateString.trim().equals("null"))
            return null;
        DateFormat df = new SimpleDateFormat(type);
        Date date = new Date();
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block\
            Log.e("StringToDate", dateString + "    " + e);
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转出dateforamt
     * @param dateString
     * @param oldtype
     * @param newtype
     * @return
     */
    public static String changeFormat(String dateString, String oldtype, String newtype ) {
        if (dateString == null || dateString.trim().equals("") || dateString.trim().equals("null"))
            return "";
        DateFormat df = new SimpleDateFormat(oldtype);
        Date date = new Date();
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block\
            Log.e("StringToDate", dateString + "    " + e);
            e.printStackTrace();
        }
        return Date2String(date, newtype);
    }

    /**
     * 将date对象转换成String
     * @param date
     * @param format
     * @return
     */
    public static String Date2String(Date date, String format){
        SimpleDateFormat dateformat=new SimpleDateFormat(format);
        return dateformat.format(date);
    }

    /**
     * calendar  转String
     * @param date
     * @param format
     * @return
     */
    public static String Date2String(Calendar date, String format){
        SimpleDateFormat dateformat=new SimpleDateFormat(format);
        return dateformat.format(date.getTime());
    }

    /**
     * 获取00:12:25 格式的时间字符串
     * @param lenth
     * @return
     */
    public static String initProgramLength(int lenth) {
        int lengh = lenth;
        int hour = lengh / 3600;
        int min = lengh % 3600 / 60;
        int sec = lengh % 60;
        String programLength = null;
        if (hour == 0)
            programLength = "时长" + min + ":" + sec;
        else
            programLength = "时长 " + hour + ":" + min + ":" + sec;
        return programLength;
    }


    /**
     * 根据utc返回 hh:mm
     * @param UTCTime
     * @return 00:00这样的 小时：分
     */
    public static String getLocalTimeFromUTC(long UTCTime) {
        String result;

        Date d = new Date(UTCTime);
        Calendar cal= Calendar.getInstance();
        cal.setTime(d);
        int hour=   cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        String hourstr;
        String minStr;
        if(hour<10){
            hourstr="0"+hour;
        }
        else{
            hourstr=""+hour;
        }
        if(min<10){
            minStr="0"+min;
        }
        else{
            minStr=""+min;
        }
        result = hourstr + ":" + minStr;
        return result;
    }
    /**
     * 将utc转换成calendar
     */

    public static Calendar getCalendarFromUTC(long UTCTime) {
        Date d = new Date(UTCTime);
        Calendar cal= Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }
    /**
     *
     * @param UTCTime
     * @return yyyy-mm--dd
     */
    public static String getDateFromUtc(long UTCTime){


        Date d = new Date(UTCTime);
        Calendar cal= Calendar.getInstance();
        cal.setTime(d);
        //str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(cal.getTime());
        return (new SimpleDateFormat("yyyy-MM-dd ")).format(cal.getTime());
    }



    /**
     *
     * @param UTCTime
     * @return 上午 -下午-晚上  1-2-3
     */
    public static int getDayFiled(long UTCTime){


        Date d = new Date(UTCTime);
        Calendar cal= Calendar.getInstance();
        cal.setTime(d);
        int hour=   cal.get(Calendar.HOUR_OF_DAY);
        if(hour<12){
            return 1;//上午
        }
        if(hour<18){
            return 2;//下午
        }else{
            return 3;//晚上
        }
    }
    /**
     *
     * @param UTCTime
     * @return 星期几
     */
    //根据UTCTime  获取星期几
    public static String getWeekOfDateByUtc(long UTCTime) {
        Date d = new Date();
        d.setTime(UTCTime);
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }


    /**
     *
     * @param UTCTime
     * @return 星期几
     */
    //根据UTCTime  获取星期几
    public static String getWeekOfDateByUtc2(long UTCTime) {
        Date d = new Date();
        d.setTime(UTCTime);
        String[] weekOfDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }



    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static String getDayForWeekByStr(String pTime) throws Exception {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));

        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return weekOfDays[dayForWeek];
    }

    /**
     * 根据字符串获取utc
     * @param pTime 2015-12-12 12：12
     * @return  utc
     */
    public static long getUtcFromStamp(String pTime){
        //设定时间的模板
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //得到指定模范的时间

        Date d = null;
        try {
            d = sdf.parse(pTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  d.getTime();
    }


    /**
     * s1是否大于s2
     * @param s1
     * @param s2
     * @return
     * @throws Exception
     */
    public static boolean DateCompare(String s1, String s2) throws Exception {
        //设定时间的模板
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //得到指定模范的时间
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);
        if(d1.getTime() - d2.getTime()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 获取今天0点的时间戳。
     * @return
     */

    public static long getToday0Mills(){
        Calendar c= Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 001);
        return c.getTimeInMillis();
    }


    /**
     * 求两个日期相差天数
     *
     * @param sd
     *            起始日期，格式yyyy-MM-dd
     * @param ed
     *            终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
                .valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 返回当前时间字符串。
     * <p>
     * 格式：yyyy-MM-dd
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentDate() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd");
    }


    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
     *
     * @param date
     *            指定的日期
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     * 取得指定年月日的日期对象.
     *
     * @param year
     *            年
     * @param month
     *            月注意是从1到12
     * @param day
     *            日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * 取得当前Date对象.
     *
     * @return Date 当前Date对象.
     */
    public static Date getDateObj() {
        Calendar c = new GregorianCalendar();
        return c.getTime();
    }

    /**
     *
     * @return 当前月份有多少天；
     */
    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
        int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }
    /**
     * 根据指定的年月 返回指定月份（yyyy-MM）有多少天。
     *
     * @param time yyyy-MM
     * @return 天数，指定月份的天数。
     */
    public static int getDaysOfCurMonth(final String time) {
        String[] timeArray = time.split("-");
        int curyear = new Integer(timeArray[0]).intValue(); // 当前年份
        int curMonth = new Integer(timeArray[1]).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        if (curMonth == 12) {
            return mArray[0];
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param year
     * @param month
     *            month是从1开始的12结束
     * @param day
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *            "yyyy/MM/dd",或者"yyyy-MM-dd"
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String date) {
        String[] temp = null;
        if (date.indexOf("/") > 0) {
            temp = date.split("/");
        }
        if (date.indexOf("-") > 0) {
            temp = date.split("-");
        }
        return getDayOfWeek(temp[0], temp[1], temp[2]);
    }

    /**
     *  返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getChinaDayOfWeek(String date){
        String[] weeks = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int week = getDayOfWeek(date);
        return weeks[week-1];
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 根据给定的格式，返回时间字符串。
     * <p>
     * 格式参照类描绘中说明.
     *
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }
}
