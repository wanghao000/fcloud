package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EquipmentData;

import java.util.List;

public interface EquipmentDataMapper {
	List<EquipmentData> findAll();
	List<EquipmentData> findOne(String imei);
	void addRecord(EquipmentData equipmentData);
}
