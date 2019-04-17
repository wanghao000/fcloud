package cn.hz.fcloud.utils;

import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.service.EquipmentService;
import org.json.JSONObject;

import java.util.List;

public class UDPServerUtil {
    public static String toJsonString(String code, String msg, String date){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("datetime", date);
        return jsonObject.toString();
    }

    public static void findExceedTimeRecord(EquipmentService equipmentService, long limitTime){
        List<Equipment> all = equipmentService.findAll();
        for (Equipment equipment : all) {
            System.out.println(equipment);
            System.out.println(equipment.getLastReportTime());
            if (System.currentTimeMillis()-equipment.getLastReportTime().getTime()>limitTime){
                equipmentService.updateReportTimeAndOnline(new Equipment(equipment.getCode(), 0, null));
            }
        }
    }
}
