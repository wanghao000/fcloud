package cn.hz.fcloud.service;


import cn.hz.fcloud.entity.EquipmentDataAndName;

import java.util.List;

public interface EquipmentDataAndNameService {

    /**
     * 获取当天所有告警数据
     * @return 告警数据列表
     */
    List<EquipmentDataAndName> findAll();

    /**
     * 根据公司id获取告警数据
     * @param id 公司id
     * @return 告警数据列表
     */
    List<EquipmentDataAndName> findByComId(Long id);
}
