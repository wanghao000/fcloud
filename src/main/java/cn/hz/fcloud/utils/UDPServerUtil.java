package cn.hz.fcloud.utils;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.websocket.CigaretteWebsocketHandler;
import cn.hz.fcloud.websocket.CigaretteWebsocketHandlerInterceptor;
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
            if (System.currentTimeMillis()-equipment.getLastReportTime().getTime()>limitTime && equipment.getIsOnline()==1){
                equipmentService.updateReportTimeAndOnline(new Equipment(equipment.getCode(), 0, null));
            }
        }
    }

    public static void sendMsgIf(SysUserService sysUserService, CompanyService companyService, EquipmentService equipmentService, String imei, String msg){
        for (String username : CigaretteWebsocketHandlerInterceptor.usernames) {
            SysUser sysUser = sysUserService.queryByUserName(username);
            if (sysUser.getType() == 1) {
                CigaretteWebsocketHandler.sendMessageToUser(username, msg);
            } else if(sysUser.getType() == 2) {
                List<Company> companyListByProviderId = companyService.getCompanyListByProviderId(sysUser.getProviderId());
                if (companyListByProviderId != null && companyListByProviderId.size()>0) {
                    for (Company company : companyListByProviderId) {
                        if(company.getId() == equipmentService.findOne(imei).getCompanyId()) {
                            CigaretteWebsocketHandler.sendMessageToUser(username, msg);
                        }
                    }
                }
            } else if(sysUser.getType() == 3) {
                if(sysUser.getCompanyId() != null && sysUser.getCompanyId() == equipmentService.findOne(imei).getCompanyId()) {
                    CigaretteWebsocketHandler.sendMessageToUser(username, msg);
                }
            }
        }
    }

    public static String returnHtmlJson(String imei, String msg, int type, String date){
        JSONObject returnJson = new JSONObject();
        returnJson.put("imei", imei);
        returnJson.put("msg", msg);
        returnJson.put("type", type);
        returnJson.put("date", date);
        return returnJson.toString();
    }
}
