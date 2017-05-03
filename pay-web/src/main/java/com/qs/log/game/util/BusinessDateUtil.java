package com.qs.log.game.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * //@Author:zun.wei, @Date:2017/4/5 11:50
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public class BusinessDateUtil {


     /* public static void main(String[] args) throws Exception {

        int currentMaxDays = getCurrentMonthDay();

        int maxDaysByDate = getDaysByYearMonth(2012, 11);

        String week = getDayOfWeekByDate("2012-12-25");

        System.out.println("本月天数：" + currentMaxDays);
        System.out.println("2012年11月天数：" + maxDaysByDate);
        System.out.println("2012-12-25是：" + week);
        System.out.println("--------------------------------------");

        test();
        System.out.println("--------------------------------------");
    }*/

    /**
     * 获取当月的 天数
     * */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            //SimpleDateFormat formatter = new SimpleDateFormat("E");
            //SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");//EEE
            SimpleDateFormat formatter = new SimpleDateFormat();
            String str = formatter.format(myDate);
            dayOfweek = str;
        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }


    private static int daysBetween(Date now, Date returnDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(now);
        cReturnDate.setTime(returnDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
    }

    private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }



  /*  public static void dayReport(Date month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);//month 为指定月份任意日期
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int dayNumOfMonth = getDaysByYearMonth(year, m);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始

        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            System.out.println("df = " + df);
        }
    }*/


    /**
     * 获取代理商周信息统计 日期查询表
     * @return Map<String,List<String>>
     * @throws ParseException
     */
    public static Map<String,List<String>> getAgentInfoDateTime() throws ParseException {
        //请注意月份是从0-11
        Calendar start = Calendar.getInstance();
        start.set(2017,2, 1);//月份从0开始，这里从2017年3月开始。
        Calendar end = Calendar.getInstance();
        //end.set(2017, 3, 1);
        end.setTime(new Date());

        int sunday = 0;
        int monDay = 0;
        int year = 0;
        String mon = null;
        String sun = null;
        Map<String, List<String>> map = new HashMap<>();
        List<String> dataList = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM月dd日");
        while(start.compareTo(end) <= 0) {
            int w = start.get(Calendar.DAY_OF_WEEK);
            if (w == Calendar.MONDAY) {//星期一
                mon = formatMonth.format(start.getTime());
                monDay = 1;
            }
            if (w == Calendar.SUNDAY) {//1为星期天，7为星期六
                if (monDay == 1) {
                    sun = formatMonth.format(start.getTime());
                    sunday = 1;
                }

            }
            if (monDay == sunday && monDay == 1) {
                if (year == 0) year = start.get(Calendar.YEAR);
                if (year == start.get(Calendar.YEAR)) {
                    String data = mon + "--" + sun;
                    dataList.add(data);
                } else {
                    if (year != 0) {
                        map.put("a" + year, dataList);
                        dataList = new ArrayList<>();
                        year += 1;
                    }
                }
                monDay = 0;
                sunday = 0;
                mon = null;
                sun = null;
            }
            //打印每天2
            //System.out.println(format.format(start.getTime()));
            //循环，每次天数加1
            start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
        }
        map.put("a" + year, dataList);//当前年的数据。
        //System.out.println("map = " + map);
        return map;
    }















    public static void test_bak() throws ParseException {
        //请注意月份是从0-11
        Calendar start = Calendar.getInstance();
        start.set(2017,0, 1);//月份从0开始
        Calendar end = Calendar.getInstance();
        //end.set(2017, 3, 1);
        end.setTime(new Date());

        int sumSunday = 0;
        int sumSat = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while(start.compareTo(end) <= 0) {
            int w = start.get(Calendar.DAY_OF_WEEK);
            if(w == Calendar.SUNDAY)
                sumSunday ++;
            if(w == Calendar.SATURDAY)
                sumSat ++;
            //打印每天2
            System.out.println(format.format(start.getTime()));
            //循环，每次天数加1
            start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
        }
        System.out.println("星期天总数为:" + sumSunday);
        System.out.println("星期六总数为:" + sumSat);

    }




    /**
     * 获取三个月前的时间
     */
    public static void get3MonthTime() {
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(calendar.MONTH, -3);  //设置为前3月
        dBefore = calendar.getTime();   //得到前3月的时间

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
        String defaultEndDate = sdf.format(dNow); //格式化当前时间

        System.out.println("前3个月的时间是：" + defaultStartDate);
        System.out.println("生成的时间是：" + defaultEndDate);
    }


    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:16
     *  获取上周一的时间
     * @return
     */
    public static String getLastWeekMonday() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date date1 = cal.getTime();
        //return cal.getTime();
        return simpleDateFormat.format(date1);
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:16
     * 获取上周日的时间
     * @return
     */
    public static String getLastWeekSunday() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        Date date1 = cal.getTime();
        //return cal.getTime();
        return simpleDateFormat.format(date1);
    }

    /**
     * 获取本周的周一日期.
     * @return
     */
    public static Date getNowWeekMonday() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

}