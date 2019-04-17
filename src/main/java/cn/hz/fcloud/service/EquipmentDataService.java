package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.EquipmentData;

import java.util.List;

public interface EquipmentDataService {
    List<EquipmentData> findAll();
    List<EquipmentData> findOne(String imei);
    void addRecord(EquipmentData equipmentData);
    List<EquipmentData> findType(int type);
}
