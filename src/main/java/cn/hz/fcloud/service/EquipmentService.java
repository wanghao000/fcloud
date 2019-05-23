package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Equipment;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    List<Equipment> findAll();
    Equipment findOne(String code);
    void addEquipment(Equipment equipment);
    int delEquipment(String code);
	void updateReportTimeAndOnline(Equipment equipment);
	int lineCount();
    int countAll();
    List<Equipment> getEquipmentList(Long companyId);
    int updateEq(Equipment eq);
	int modifyState(String code,int isDelete);
    List<Map<String, Object>> findTypeAndCount();
    List<Map<String, Object>> findTypeAndCountByUser(long userId);
    List<Map<String, Object>> findCompanyAndCount();
    List<Map<String, Object>> findCompanyAndCountByUser(long userId);
}
