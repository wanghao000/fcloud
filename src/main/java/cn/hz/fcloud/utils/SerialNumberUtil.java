package cn.hz.fcloud.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class SerialNumberUtil {

    public static String generateNumner(){
        LocalDateTime time = LocalDateTime.now();
        String year = ""+time.getYear();
        String month = time.getMonthValue()>=10?""+time.getMonthValue():"0"+time.getMonthValue();
        String day = time.getDayOfMonth()>=10?""+time.getDayOfMonth():"0"+time.getDayOfMonth();
        int uuid = UUID.randomUUID().toString().hashCode();
        if(uuid<0){
            uuid = -uuid;
        }
        String uid = String.format("%09d",uuid);
        return year+month+day+uid;
    }
}
