package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAll();
    Equipment findOne(String code);
    void addEquipment(Equipment equipment);
    void delEquipment(String code);
	void updateReportTimeAndOnline(Equipment equipment);	int lineCount();
    int countAll();
    List<Equipment> getEquipmentList(Long id);}
