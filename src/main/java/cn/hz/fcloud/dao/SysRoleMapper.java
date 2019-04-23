package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    List<SysRole> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);
}
