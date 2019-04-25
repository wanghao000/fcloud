package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EqInfosMapper;
import cn.hz.fcloud.entity.EqInfos;
import cn.hz.fcloud.service.EqInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EqInfosServiceImpl implements EqInfosService {

    @Autowired
    EqInfosMapper mapper;

    //根据设备编号获取设备详细信息
    @Override
    public EqInfos findOne(String code){
        return mapper.findOne(code);
    }

    //获取所有设备详细信息
    @Override
    public List<EqInfos> findAll(Map<String,Object> map){
        return mapper.findAll(map);
    }

    @Override
    public int findAllCount(Map<String,Object> map){
        return mapper.findAllCount(map);
    }


    //根据公司id获取设备信息
    @Override
   public  List<EqInfos> findByComId(Map<String,Object> map){
        return mapper.findByComId(map);
    }

    @Override
    public  int findByComIdCount(Map<String,Object> map){
        return mapper.findByComIdCount(map);
    }
    //根据服务商id获取设备信息
    @Override
    public List<EqInfos> findByProviderId(Map<String,Object> map){
        return mapper.findByProviderId(map);
    }

    @Override
    public int findByProviderIdCount(Map<String,Object> map){
        return mapper.findByProviderIdCount(map);
    }

}
