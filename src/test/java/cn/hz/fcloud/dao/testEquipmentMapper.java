package cn.hz.fcloud.dao;

import cn.hz.fcloud.BaseTest;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testEquipmentMapper extends BaseTest {
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Test
    public void testEquipmentMapper(){
        List<Equipment> list=equipmentMapper.test();
        System.out.println(list.get(0).getDataList().size());
    }
}
