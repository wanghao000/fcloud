package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EquipmentData;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipmentDataAndNameMapper {

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
