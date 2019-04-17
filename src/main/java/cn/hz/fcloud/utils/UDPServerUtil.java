package cn.hz.fcloud.utils;

import org.json.JSONObject;

public class UDPServerUtil {
    public static String toJsonString(String code, String msg, String date){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("datetime", date);
        return jsonObject.toString();
    }
}
