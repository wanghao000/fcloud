package cn.hz.fcloud.dao;

import cn.hz.fcloud.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class testEquipmentDataMapper extends BaseTest {
    @Autowired
    private EquipmentDataMapper equipmentDataMapper;

    @Test
    public void testData(){
        List<Map<String,Object>> map=equipmentDataMapper.AlarmTrend();
        System.out.println(map);
    }
}
