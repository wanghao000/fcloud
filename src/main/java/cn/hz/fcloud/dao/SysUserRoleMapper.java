package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;
import java.util.Map;

public interface SysUserRoleMapper {
    /**
     * 保存用户与角色的关系
     * @param map
     * @return
     */
    int save(Map<String, Object> map);

    /**
     * 删除用户与角色关系
     * @param id
     * @return
     */
    int delete(Object id);

    /**
     * 查询id包含的角色
     * @param id
     * @return
     */
    List<RolePOJO> queryHasRole(long id);
}
