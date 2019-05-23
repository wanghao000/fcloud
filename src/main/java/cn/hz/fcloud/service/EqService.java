package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Eq;

import java.util.List;

public interface EqService {

    /**
     * 根据公司id获取设备信息以及是否告警
     * @param id 公司id
     * @return
     */
    List<Eq> findByComId(Long id);
}
