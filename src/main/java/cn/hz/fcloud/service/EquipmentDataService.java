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
     * 获取设备运行记录
     * @param map
     * @return
     */
    List<EquipmentData> getInfoByCode(Map<String,Object> map);

    /**
     * 统计设备运行记录总数
     * @param map
     * @return
     */
    int getInfoCount(Map<String,Object> map);
}
