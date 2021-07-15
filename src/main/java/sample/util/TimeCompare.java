package sample.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCompare {
    private static int Time2Int(String ftDate) {
        /**
         *  将HH:mm:ss时间转成秒
         */
        String[] splitTime = ftDate.split(":");
        int h = Integer.parseInt(splitTime[0]);
        int m = Integer.parseInt(splitTime[1]);
        int s = Integer.parseInt(splitTime[2]);
        return h * 3600 + m * 60 + s;
    }
    public static boolean CompareNowTime(Date cTime) {
        /**
         * 与现在的时间比较，大于现在的时间就返回ture
         */
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        return Time2Int(ft.format(cTime)) >= Time2Int(ft.format(date));
    }
    public static boolean CompareNowTime(String cTime) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        return Time2Int(cTime) >= Time2Int(ft.format(date));
    }
    public static boolean CompareTime(Date T1, Date T2) {
        /**
         * 两个日期相比较返回 T1 >= T2 ?
         */
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        String t1 = ft.format(T1);
        String t2 = ft.format(T2);
        return Time2Int(t1) >= Time2Int(t2);
    }
}