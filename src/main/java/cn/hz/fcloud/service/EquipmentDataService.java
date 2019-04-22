package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import org.apache.ibatis.annotations.Param;

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

    List<Map<String,Object>> alertTable(@Param("ids") List<Long> ids);

    List<Map<String,Object>> lineChartMap(List<Long> ids);
}
