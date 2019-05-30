package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Eq;

import java.util.List;

public interface EqMapper {

    /**
     * 根据公司id获取设备信息以及是否告警
     * @param id 公司id
     * @return
     */
    List<Eq> findByComId(Long id);

    /**
     * 获取所有设备信息以及是否告警
     * @return
     */
    List<Eq> findAllEqs();

    /**
     * 根据公司id获取设备信息以及是否告警
     * @param id 服务商id
     * @return
     */
    List<Eq> findByProvider(Long id);
}
