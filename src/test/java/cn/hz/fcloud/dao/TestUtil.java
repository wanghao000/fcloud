package cn.hz.fcloud.dao;

import cn.hz.fcloud.BaseTest;
import cn.hz.fcloud.utils.SerialNumberUtil;
import org.apache.xpath.operations.Bool;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestUtil extends BaseTest{

    @Test
    public void testNumber(){
        Set set = new HashSet();
        Boolean flag = true;
        for(int i=0;i<1000000;i++){
            flag = set.add(SerialNumberUtil.generateNumner());
            if(!flag && i<10000){
                System.out.println(i);
            }
        }
    }
    @Test
    public void testThread(){
        for(int j=0;j<1000;j++){
            testNumber();
        }
    }
}
