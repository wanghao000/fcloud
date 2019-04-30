package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;

public interface SysUserRoleService {
    void saveOrUpdate(Long userId, List<Long> roleIdList);
    List<RolePOJO> haveRole(long id);
}
