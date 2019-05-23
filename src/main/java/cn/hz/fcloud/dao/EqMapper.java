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
}
