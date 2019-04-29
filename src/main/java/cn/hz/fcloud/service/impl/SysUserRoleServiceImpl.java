package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysUserRoleMapper;
import cn.hz.fcloud.entity.pojo.RolePOJO;
import cn.hz.fcloud.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        sysUserRoleMapper.delete(userId);
        if(roleIdList.size() != 0){
            //保存用户与角色关系
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("roleIdList", roleIdList);
            sysUserRoleMapper.save(map);
        }

    }

    @Override
    public List<RolePOJO> haveRole(long id) {
        return sysUserRoleMapper.queryHasRole(id);
    }
}
