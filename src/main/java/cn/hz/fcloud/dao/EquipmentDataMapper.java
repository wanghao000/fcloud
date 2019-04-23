package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

	List<EquipmentDataAndName> AlarmListByCode(List<String> codes);

	List<Map<String,Object>> AlarmTrend();

	List<Map<String,Object>> alarmTable(@Param("ids") List<Long> ids);

	List<Map<String,Object>> lineChartMap(List<Long> ids);

	List<Map<String, Object>> findAlermEquipmentAndCount();
}
