package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EqInfos;
import java.util.List;

public interface EqInfosMapper {
    //根据设备编号获取设备详细信息
    EqInfos findOne(String code);

    //获取所有设备详细信息
    List<EqInfos> findAll();

    //根据公司id获取设备信息
    List<EqInfos> findByComId(Long id);

    List<EqInfos>findByProviderId(Long id);
}