package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    /**
     *显示全部角色列表
     * @param map
     * @return
     */
    List<SysRole> queryList(Map<String, Object> map);

    /**
     * 全部列表的总数
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存
     * @param role
     */
    void save(SysRole role);

    /**
     * 删除
     * @param roleId
     */
    void delete(Long roleId);

    /**
     * 查询单个角色对象
     * @param roleId
     * @return
     */
    SysRole queryObject(Long roleId);

    /**
     * 更新角色
     * @param role
     */
    void update(SysRole role);

    List<SysRole> getNotIn(List<RolePOJO> lists);
}
