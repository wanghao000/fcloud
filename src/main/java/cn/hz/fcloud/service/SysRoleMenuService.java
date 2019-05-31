package cn.hz.fcloud.service;

import java.util.List;

public interface SysRoleMenuService {
    /**
     * 保存或更新
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
