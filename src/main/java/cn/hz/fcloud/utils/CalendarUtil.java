package cn.hz.fcloud.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    public static Date afterHours(int hours){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }
}
