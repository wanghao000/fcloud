package cn.hz.fcloud.dao;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuMapper {
    /**
     * 保存角色与菜单关系
     * @param map
     */
    void save(Map<String, Object> map);

    /**
     * 根据id删除角色与菜单关系
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
