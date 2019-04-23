package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    List<SysRole> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);
}
