package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysRoleMapper;
import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> queryList(Map<String, Object> map) {
        return sysRoleMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleMapper.queryTotal(map);
    }
}
