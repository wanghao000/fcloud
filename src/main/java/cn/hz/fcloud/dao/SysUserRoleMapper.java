package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;
import java.util.Map;

public interface SysUserRoleMapper {
    int save(Map<String, Object> map);
    int delete(Object id);

    List<RolePOJO> queryHasRole(long id);
}
