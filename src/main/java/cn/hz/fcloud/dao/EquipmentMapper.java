package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipmentMapper {
    List<Equipment> findAll();
    Equipment findOne(String code);
    void addEquipment(Equipment equipment);
    int delEquipment(String code);
	void updateReportTimeAndOnline(Equipment equipment);	/**
     * 今日离线所有设备数量
     * @return
     */
    int lineCount();

    int countAll();
    //企业下设备列表
    List<Equipment> selectByCompanyId(Long companyId);

    List<Equipment> test();
    int updateEq(Equipment eq);
	int modifyState(@Param("code") String code, @Param("isDelete") int isDelete);
    List<Map<String, Object>> findTypeAndCount();
    List<Map<String, Object>> findTypeAndCountByUser(long userId);
    List<Map<String, Object>> findCompanyAndCount();
    List<Map<String, Object>> findCompanyAndCountByUser(long userId);
}
