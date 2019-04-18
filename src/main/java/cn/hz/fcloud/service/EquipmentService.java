package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAll();
    Equipment findOne(int code);
    void addEquipment(Equipment equipment);
    void delEquipment(int code);

    int lineCount();

    int countAll();

    List<Equipment> getEquipmentList(Long id);
}
