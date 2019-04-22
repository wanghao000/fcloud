package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;

import java.util.List;
import java.util.Map;

public interface EquipmentDataService {
    List<EquipmentData> findAll();
    List<EquipmentData> findOne(String imei);
    void addRecord(EquipmentData equipmentData);
    List<EquipmentData> findType(int type);

    int getAlarmCount();

    int geetAlarmCountByCode(String code);

    List<EquipmentDataAndName> getAlarmList(List<String> codes);

    List<Map<String,Object>> getAlertMap();
}
