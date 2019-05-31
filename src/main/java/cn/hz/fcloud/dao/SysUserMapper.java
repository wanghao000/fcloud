package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    /**
     * 删除根据id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(SysUser record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(SysUser record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * 选择性的更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);
    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 获取用户列表
     * @param map
     * @return
     */
    List<SysUser> queryList(Map<String,Object> map);

    /**
     * 用户列表总数
     * @param map
     * @return
     */
    Integer queryListCount(Map<String,Object> map);
}