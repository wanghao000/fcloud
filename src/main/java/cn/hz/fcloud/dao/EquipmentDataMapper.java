package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EquipmentData;

import java.util.List;

public interface EquipmentDataMapper {
	List<EquipmentData> findAll();
	List<EquipmentData> findOne(String imei);
	void addRecord(EquipmentData equipmentData);
	List<EquipmentData> findType(int type);

	/**
	 * 获取今日设备全部告警数量
	 * @return
	 */
	int alarmCount();

	int alarmCountByCode(String code);

	List<EquipmentData> AlarmListByCode(List<String> codes);
}
