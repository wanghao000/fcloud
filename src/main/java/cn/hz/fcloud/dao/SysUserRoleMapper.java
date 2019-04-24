package cn.hz.fcloud.dao;

import java.util.Map;

public interface SysUserRoleMapper {
    int save(Map<String, Object> map);
    int delete(Object id);
}
