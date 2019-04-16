package cn.hz.fcloud.dao;

import cn.hz.fcloud.BaseTest;
import cn.hz.fcloud.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestSysUserMapper extends BaseTest {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    public void testQueryByUserName(){
        SysUser user = sysUserMapper.queryByUserName("admin");
        System.out.println(user.getId());
    }
}
