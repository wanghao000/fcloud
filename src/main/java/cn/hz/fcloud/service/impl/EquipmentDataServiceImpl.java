package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EquipmentDataMapper;
import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.service.EquipmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
