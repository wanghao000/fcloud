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
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UDPServerUtil {
    private static final byte[] key = {(byte) 0xEC,(byte) 0xAD,0x51,0x5A,0x44,0x41,(byte) 0xCE,(byte) 0xDA};

    /**
     * 获取base.properties中encryption属性决定是否加解密程序
     */
    public static boolean getEncryptionConfig(){
        ClassPathResource resource = new ClassPathResource("/base.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(resource.getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(properties.getProperty("encryption"));
    }

    /**
     *
     * @param b 需要加解密的byte[]
     * @param length 数组的长度
     */
    public static void encryption(byte[] b,int length){
        int keyIndex = 0;
        for (int i = 4; i < length-4; i++) {
            b[i] ^= key[keyIndex];
            if(keyIndex == key.length-1) {
                keyIndex = 0;
                continue;
            }
            keyIndex++;
        }
    }

    /**
     *
     * @param code 设备imei
     * @param msg 上报的信息
     * @param date 上报时间
     * @return
     */
    public static String toJsonString(String code, String msg, String date){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("datetime", date);
        return jsonObject.toString();
    }

    /**
     * 查找超时记录
     * @param equipmentService
     * @param limitTime 允许的时长（毫秒）
     */
    public static void findExceedTimeRecord(EquipmentService equipmentService, long limitTime){
        List<Equipment> all = equipmentService.findAll();
        for (Equipment equipment : all) {
            if (equipment.getLastReportTime() != null && System.currentTimeMillis()-equipment.getLastReportTime().getTime()>limitTime && equipment.getIsOnline()==1){
                equipmentService.updateReportTimeAndOnline(new Equipment(equipment.getCode(), 0, null));
            }
        }
    }

    /**
     * 向页面发送websocket提示
     */
    public static void sendMsgIf(SysUserService sysUserService, CompanyService companyService, EquipmentService equipmentService, String imei, String msg){
        for (String username : CigaretteWebsocketHandlerInterceptor.usernames) {
            SysUser sysUser = sysUserService.queryByUserName(username);
            if (sysUser.getType() == 1) {
                CigaretteWebsocketHandler.sendMessageToUser(username, msg);
            } else if(sysUser.getType() == 2) {
                List<Company> companyListByProviderId = companyService.getCompanyListByProviderId(sysUser.getProviderId());
                if (companyListByProviderId != null && companyListByProviderId.size()>0) {
                    for (Company company : companyListByProviderId) {
                        if(company.getId().intValue() == equipmentService.findOne(imei).getCompanyId().intValue()) {
                            CigaretteWebsocketHandler.sendMessageToUser(username, msg);
                        }
                    }
                }
            } else if(sysUser.getType() == 3) {
                Equipment equipment = equipmentService.findOne(imei);
                if(equipment!= null && sysUser.getCompanyId().intValue() == equipment.getCompanyId().intValue()) {
                    CigaretteWebsocketHandler.sendMessageToUser(username, msg);
                }
            }
        }
    }

    /**
     * 将要发送的数据包装成json
     */
    public static String returnHtmlJson(String imei, String msg, int type, String date){
        JSONObject returnJson = new JSONObject();
        returnJson.put("imei", imei);
        returnJson.put("msg", msg);
        returnJson.put("type", type);
        returnJson.put("date", date);
        return returnJson.toString();
    }
}
