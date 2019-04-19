package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysChina;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description
 * @Author wh
 * @Date
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring.xml"})
public class TestSysChinaMapper {

    @Autowired
    private SysChinaMapper mapper;
    @Test
    public void test(){
        List<SysChina> list = mapper.selectByParentid(320000);
        for(SysChina china : list){
            System.out.println(china.getMerger_short_name());
        }
    }
}
