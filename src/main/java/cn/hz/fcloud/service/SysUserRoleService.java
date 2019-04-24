package cn.hz.fcloud.service;

import java.util.List;

public interface SysUserRoleService {
    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
