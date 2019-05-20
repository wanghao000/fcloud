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

	List<Map<String, Object>> findAlarmEquipmentAndCount();

	List<Map<String, Object>> findAlarmEquipmentAndCountByUser(long userId);

	List<Map<String, Object>> findCompanyAlarmCount();

	List<Map<String, Object>> findCompanyAlarmCountByUser(long userId);

	List<Map<String, Object>> find7dayAlarmCount();

	List<Map<String, Object>> find7dayAlarmCountByUser(long userId);

	List<Map<String, Object>> recent5Record();

	List<Map<String, Object>> recent5RecordByUser(long userId);

	List<Map<String, Object>> findAreaAlarmCount();

	List<Map<String, Object>> findAreaAlarmCountByUser(long userId);

	/**
	 * 根据code查询设备数据
	 * @param map
	 * @author 陈信晨
	 */
	List<EquipmentData> selectByCode(Map<String,Object> map);

	/**
	 * 统计设备数据
	 * @param map
	 * @author 陈信晨
	 */
	int selectByCodeCount(Map<String,Object> map);
}
