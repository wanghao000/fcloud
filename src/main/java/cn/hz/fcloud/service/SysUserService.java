package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysUser;

import java.util.List;
import java.util.Map;

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

    void save(SysUser user);

    SysUser selectById(Long id);

    int update(SysUser user);

    Integer queryListCount(Map<String,Object> map);

    int insert(SysUser sysUser);
}
