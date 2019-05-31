package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.entity.pojo.RolePOJO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    /**
     * 查询角色列表
     * @param map
     * @return
     */
    List<SysRole> queryList(Map<String, Object> map);

    /**
     * 列表数量查询
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存
     * @param sysRole
     */
    void save(SysRole sysRole);

    /**
     * 删除
     * @param roleId
     */
    void delete(long roleId);

    /**
     * 查询一个角色
     * @param id
     * @return
     */
    SysRole queryObject(long id);

    /**
     * 更新一个角色
     * @param sysRole
     * @return
     */
    int update(SysRole sysRole);

    List<SysRole> notIn(@Param("ids") Long[] ids);
}
