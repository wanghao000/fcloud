package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EqInfos;
import java.util.List;
import java.util.Map;

public interface EqInfosMapper {
    //根据设备编号获取设备详细信息
    EqInfos findOne(String code);

    //获取所有设备详细信息
    List<EqInfos> findAll(Map<String,Object> map);

    int findAllCount(Map<String,Object> map);
    //根据公司id获取设备信息
    List<EqInfos> findByComId(Map<String,Object> map);

    int findByComIdCount(Map<String,Object> map);

    List<EqInfos> findByProviderId(Map<String,Object> map);

    int findByProviderIdCount(Map<String,Object> map);
}
