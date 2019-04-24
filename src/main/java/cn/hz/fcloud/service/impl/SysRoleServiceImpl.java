package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysRoleMapper;
import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.service.SysRoleMenuService;
import cn.hz.fcloud.service.SysRoleService;
import cn.hz.fcloud.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> queryList(Map<String, Object> map) {
        return sysRoleMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleMapper.queryTotal(map);
    }

    @Override
    public void save(SysRole role) {
        role.setCreateTime(new Date());
        sysRoleMapper.save(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void delete(Long roleId) {
        if(roleId==1||roleId==2||roleId==3||roleId==4){
            throw new ServiceException("系统级角色不可删除");
        }
        sysRoleMapper.delete(roleId);
    }

    @Override
    public SysRole queryObject(Long roleId) {
        return sysRoleMapper.queryObject(roleId);
    }

    @Override
    public void update(SysRole role) {
        sysRoleMapper.update(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }
}
