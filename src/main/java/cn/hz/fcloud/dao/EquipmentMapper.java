package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Equipment;

import java.util.List;

public interface EquipmentMapper {
    List<Equipment> findAll();
    Equipment findOne(int code);
    void addEquipment(Equipment equipment);
    void delEquipment(int code);
}
