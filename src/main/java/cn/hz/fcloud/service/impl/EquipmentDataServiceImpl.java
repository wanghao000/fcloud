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
    public List<Map<String, Object>> findAlarmEquipmentAndCount() {
        return equipmentDataMapper.findAlarmEquipmentAndCount();
    }

    @Override
    public List<Map<String, Object>> findAlarmEquipmentAndCountByUser(long userId) {
        return equipmentDataMapper.findAlarmEquipmentAndCountByUser(userId);
    }

    @Override
    public List<Map<String, Object>> findCompanyAlarmCount() {
        return equipmentDataMapper.findCompanyAlarmCount();
    }

    @Override
    public List<Map<String, Object>> findCompanyAlarmCountByUser(long userId) {
        return equipmentDataMapper.findCompanyAlarmCountByUser(userId);
    }

    @Override
    public List<Map<String, Object>> find7dayAlarmCount() {
        return equipmentDataMapper.find7dayAlarmCount();
    }

    @Override
    public List<Map<String, Object>> find7dayAlarmCountByUser(long userId) {
        return equipmentDataMapper.find7dayAlarmCountByUser(userId);
    }

    @Override
    public List<Map<String, Object>> recent5Record() {
        return equipmentDataMapper.recent5Record();
    }

    @Override
    public List<Map<String, Object>> recent5RecordByUser(long userId) {
        return equipmentDataMapper.recent5RecordByUser(userId);
    }

    @Override
    public List<Map<String, Object>> findAreaAlarmCount() {
        return equipmentDataMapper.findAreaAlarmCount();
    }

    @Override
    public List<Map<String, Object>> findAreaAlarmCountByUser(long userId) {
        return equipmentDataMapper.findAreaAlarmCountByUser(userId);
    }
}
