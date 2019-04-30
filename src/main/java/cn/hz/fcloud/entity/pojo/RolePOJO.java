package cn.hz.fcloud.entity.pojo;

import cn.hz.fcloud.entity.SysRole;

public class RolePOJO extends SysRole {
    private String roleName;

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
