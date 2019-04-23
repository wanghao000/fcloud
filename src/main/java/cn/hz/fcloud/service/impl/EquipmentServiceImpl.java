package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EquipmentMapper;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> findAll() {
        return equipmentMapper.findAll();
    }

    @Override
    public Equipment findOne(String code) {
        return equipmentMapper.findOne(code);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipmentMapper.addEquipment(equipment);
    }

    @Override
    public int  delEquipment(String code) {
        return  equipmentMapper.delEquipment(code);
    }
	@Override
    public void updateReportTimeAndOnline(Equipment equipment) {
        equipmentMapper.updateReportTimeAndOnline(equipment);
    }
	@Override
    public int lineCount() {
        return equipmentMapper.lineCount();
    }

    @Override
    public int countAll() {
        return equipmentMapper.countAll();
    }

    @Override
    public List<Equipment> getEquipmentList(Long id) {
        return equipmentMapper.selectByCompanyId(id);
    }

    @Override
    public int updateEq(Equipment eq){
        return equipmentMapper.updateEq(eq);
    }
	
	@Override
    public int modifyState(String code,int isDelete){
        return equipmentMapper.modifyState(code,isDelete == 1?0:1);
    }

	@Override
	public List<Map<String, Object>> findTypeAndCount(){
		return equipmentMapper.findTypeAndCount();
	}

    @Override
    public List<Map<String, Object>> findTypeAndCountByUser(long userId) {
        return equipmentMapper.findTypeAndCountByUser(userId);
    }
}
