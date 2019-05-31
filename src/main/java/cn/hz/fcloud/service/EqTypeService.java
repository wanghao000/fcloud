package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.EquipmentType;

import java.util.List;

public interface EqTypeService {

    /**
     * 获取所有的设备类型
     * @return
     */
    List<EquipmentType> findAllTypes();

    /**
     * 添加新的设备类型
     * @param equipmentType 设备类型
     * @return
     */
    void insertType(EquipmentType equipmentType);
}
