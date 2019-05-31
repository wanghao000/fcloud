package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;

/**
 * 用户角色服务
 */
public interface SysUserRoleService {
    /**
     * 保存或更新
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 用户包含角色
     * @param id
     * @return
     */
    List<RolePOJO> haveRole(long id);
}
