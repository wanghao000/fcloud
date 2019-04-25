package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 查询菜单列表
     */
    List<SysMenu> queryList(Map<String, Object> map);
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 查询菜单列表（不带分页）总数
     * @return
     */
    int queryTotal();

    SysMenu queryObject(Long menuId);


}