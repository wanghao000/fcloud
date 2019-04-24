package cn.hz.fcloud.dao;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuMapper {
    void save(Map<String, Object> map);

    int delete(long id);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
