package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.entity.pojo.RolePOJO;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    List<SysRole> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRole role);

    void delete(Long roleId);

    SysRole queryObject(Long roleId);

    void update(SysRole role);

    List<SysRole> getNotIn(List<RolePOJO> lists);
}
