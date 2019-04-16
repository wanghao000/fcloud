package cn.hz.fcloud.utils;

import cn.hz.fcloud.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {
    public static SysUser getUserEntity() {
        return (SysUser)SecurityUtils.getSubject().getPrincipal();
    }
}
