package cn.hz.fcloud.dao;

import cn.hz.fcloud.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestProviderMapper extends BaseTest {
    @Autowired
    private ProviderMapper providerMapper;
    @Test
    public void testRanking(){
        System.out.println(providerMapper.alarmRanking(0));

    }
}
