package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EquipmentMapper;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> findAll() {
        return equipmentMapper.findAll();
    }

    @Override
    public Equipment findOne(int code) {
        return equipmentMapper.findOne(code);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipmentMapper.addEquipment(equipment);
    }

    @Override
    public void delEquipment(int code) {
        equipmentMapper.delEquipment(code);
    }
}
