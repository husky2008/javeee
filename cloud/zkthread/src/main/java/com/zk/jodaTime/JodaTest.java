package com.zk.jodaTime;

import org.joda.time.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName JodaTest
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/9/26 15:26
 **/
public class JodaTest {

    public static void main(String[] args) {

        Long start = 1569487654000L;
        Long end = 1569492109000L;
        YearMonth yearMonth = new YearMonth();
        System.out.println(yearMonth);
        System.out.println(yearMonth.getMonthOfYear());
        System.out.println(yearMonth.toInterval());
        YearMonthDay yearMonthDay = new YearMonthDay();
        System.out.println(yearMonthDay);
        System.out.println(yearMonthDay.toInterval());
        System.out.println(PeriodType.yearMonthDayTime());

        //使用Second直接获取Period
        System.out.println(Seconds.seconds(1));

        //统计两个时间之间间隔的时差
        Period period = new Period(start, end, PeriodType.yearMonthDayTime());


        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        //System.out.println(period.getDays());
        System.out.println(period.getHours());
        System.out.println(period.getMinutes());


        //String time = "1569486820000,1569486830000,1569486840000,1569486850000,1569486870000,1569486880000,1569486890000,1569486900000,1569486910000,1569486920000,1569486930000";
        //String time = "1569492000000,1569492060000,1569492120000,1569492180000";
        String time = "1569492000000,1569492030000,1569492060000,1569492090000";
        Arrays.stream(time.split(",")).forEach(s -> {

            long t = Long.parseLong(s);

            t = t/10000*10000;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String format = simpleDateFormat.format(new Date(Long.valueOf(s)));
            String format = simpleDateFormat.format(t);
            System.out.println(format);

        });


    }
}
