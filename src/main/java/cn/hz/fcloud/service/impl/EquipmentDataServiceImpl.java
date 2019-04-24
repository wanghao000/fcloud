package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EquipmentDataMapper;
import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import cn.hz.fcloud.service.EquipmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipmentDataServiceImpl implements EquipmentDataService {

    @Autowired
    private EquipmentDataMapper equipmentDataMapper;

    @Override
    public List<EquipmentData> findAll() {
        return equipmentDataMapper.findAll();
    }

    @Override
    public List<EquipmentData> findOne(String imei) {
        return equipmentDataMapper.findOne(imei);
    }

    @Override
    public void addRecord(EquipmentData equipmentData) {
        equipmentDataMapper.addRecord(equipmentData);
    }

    @Override
    public List<EquipmentData> findType(int type) {
        return equipmentDataMapper.findType(type);
    }

    @Override
    public int getAlarmCount() {
        return equipmentDataMapper.alarmCount();
    }

    @Override
    public int geetAlarmCountByCode(String code) {
        return equipmentDataMapper.alarmCountByCode(code);
    }

    @Override
    public List<EquipmentDataAndName> getAlarmList(List<String> codes) {
        return equipmentDataMapper.AlarmListByCode(codes);
    }

    @Override
    public List<Map<String, Object>> getAlertMap() {
        return equipmentDataMapper.AlarmTrend();
    }

    @Override
    public List<Map<String, Object>> alertTable(List<Long> ids) {
        return equipmentDataMapper.alarmTable(ids);
    }

    @Override
    public List<Map<String, Object>> lineChartMap(List<Long> ids) {
        return equipmentDataMapper.lineChartMap(ids);
    }

    @Override
    public List<Map<String, Object>> findAlermEquipmentAndCount() {
        return equipmentDataMapper.findAlermEquipmentAndCount();
    }

    @Override
    public List<Map<String, Object>> findAlermEquipmentAndCountByUser(long userId) {
        return equipmentDataMapper.findAlermEquipmentAndCountByUser(userId);
    }
}
