package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    /**
     * 查询菜单列表
     */
    List<SysMenu> queryList(Map<String, Object> map);
    /**
     * 查询菜单
     */
    SysMenu queryObject(Long menuId);

    /**
     * 查询菜单列表（不带分页）总数
     * @return
     */
    int queryTotal();
    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Long userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);
    /**
     * 删除
     */
    void deleteBatch(Long[] menuIds);
}
