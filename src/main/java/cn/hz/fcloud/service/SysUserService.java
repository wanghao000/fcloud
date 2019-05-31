package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 系统账号
 */
public interface SysUserService {
    /**
     * 根据用户名，获取系统用户
     * @param username
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
     * 保存
     * @param user
     */
    void save(SysUser user);

    /**
     * 根据id查询账号
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 更新账号
     * @param user
     * @return
     */
    int update(SysUser user);

    /**
     * 获取表格总数
     * @param map
     * @return
     */
    Integer queryListCount(Map<String,Object> map);

    /**
     * 插入一个用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);
}
