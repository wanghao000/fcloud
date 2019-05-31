package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipmentMapper {
    /**
     * 查询所有设备
     */
    List<Equipment> findAll();
    /**
     * 通过设备imei查询设备
     */
    Equipment findOne(String code);
    /**
     * 添加设备
     */
    void addEquipment(Equipment equipment);
    /**
     * 删除设备（修改状态）
     */
    int delEquipment(String code);
    /**
     * 更新设备上报时间、是否在线
     */
	void updateReportTimeAndOnline(Equipment equipment);	/**
     * 今日离线所有设备数量
     * @return
     */
    int lineCount();
    /**
     *所有设备数量
     */
    int countAll();
    //企业下设备列表
    List<Equipment> selectByCompanyId(Long companyId);

    List<Equipment> test();
    int updateEq(Equipment eq);
	int modifyState(@Param("code") String code, @Param("isDelete") int isDelete);
    /**
     * 设备类型的数量
     */
    List<Map<String, Object>> findTypeAndCount();
    /**
     * 通过相关角色查询设备类型数量
     */
    List<Map<String, Object>> findTypeAndCountByUser(long userId);
    /**
     * 公司其下设备数量
     */
    List<Map<String, Object>> findCompanyAndCount();
    /**
     * 通过相关角色查询公司其下设备数量
     */
    List<Map<String, Object>> findCompanyAndCountByUser(long userId);
}
