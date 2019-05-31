package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {
    /**
     * 根据主键删除
     * @param menuId
     * @return
     */
    int deleteByPrimaryKey(Long menuId);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(SysMenu record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(SysMenu record);

    /**
     * 根据主键查询
     * @param menuId
     * @return
     */
    SysMenu selectByPrimaryKey(Long menuId);

    /**
     * 选择性更新根据主键
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysMenu record);
    int deleteBatch(Object[] id);
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