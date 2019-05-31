package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipmentDataMapper {
	/**
	 * 查询所有设备
	 */
	List<EquipmentData> findAll();
	/**
	 * 通过imei查找设备
	 */
	List<EquipmentData> findOne(String imei);
	/**
	 * 添加设备记录
	 */
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
	/**
	 * 报警设备及次数
	 */
	List<Map<String, Object>> findAlarmEquipmentAndCount();
	/**
	 * 根据用户查找报警设备及次数
	 */
	List<Map<String, Object>> findAlarmEquipmentAndCountByUser(long userId);
	/**
	 * 公司其下设备报警数
	 */
	List<Map<String, Object>> findCompanyAlarmCount();
	/**
	 * 根据用户公司其下设备报警数
	 */
	List<Map<String, Object>> findCompanyAlarmCountByUser(long userId);
	/**
	 * 最近7天报警数
	 */
	List<Map<String, Object>> find7dayAlarmCount();
	/**
	 * 根据用户查询最近7天报警数
	 */
	List<Map<String, Object>> find7dayAlarmCountByUser(long userId);
	/**
	 * 最近5调报警
	 */
	List<Map<String, Object>> recent5Record();
	/**
	 * 根据用户查询最近5调报警
	 */
	List<Map<String, Object>> recent5RecordByUser(long userId);
	/**
	 * 地区报警数
	 */
	List<Map<String, Object>> findAreaAlarmCount();
	/**
	 * 按角色查询其下地区报警数
	 */
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
