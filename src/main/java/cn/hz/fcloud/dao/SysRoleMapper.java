package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.entity.pojo.RolePOJO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    List<SysRole> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRole sysRole);

    void delete(long roleId);

    SysRole queryObject(long id);

    int update(SysRole sysRole);

    List<SysRole> notIn(@Param("ids") Long[] ids);
}
