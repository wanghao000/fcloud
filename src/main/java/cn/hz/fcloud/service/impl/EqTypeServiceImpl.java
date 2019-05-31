package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EqTypeMapper;
import cn.hz.fcloud.entity.EquipmentType;
import cn.hz.fcloud.service.EqTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EqTypeServiceImpl implements EqTypeService {

    @Autowired
    private EqTypeMapper eqTypeMapper;
    @Override
    public List<EquipmentType> findAllTypes() {
        return eqTypeMapper.findAllTypes();
    }

    @Override
    public void insertType(EquipmentType equipmentType) {
        eqTypeMapper.insertType(equipmentType);
    }
}
