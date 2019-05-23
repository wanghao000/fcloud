package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EquipmentDataAndNameMapper;
import cn.hz.fcloud.entity.EquipmentDataAndName;
import cn.hz.fcloud.service.EquipmentDataAndNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentDataAndNameServiceImpl implements EquipmentDataAndNameService {

    @Autowired
    private EquipmentDataAndNameMapper mapper;
    @Override
    public List<EquipmentDataAndName> findAll(){
        return mapper.findAll();
    }

    @Override
    public List<EquipmentDataAndName> findByComId(Long id){
        return mapper.findByComId(id);
    }
}
