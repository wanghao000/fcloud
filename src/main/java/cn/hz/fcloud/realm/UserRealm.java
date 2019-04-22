package cn.hz.fcloud.realm;

import cn.hz.fcloud.entity.SysMenu;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.SysMenuService;
import cn.hz.fcloud.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        List<String> permsList;
        Long userId = user.getId();
        if(userId == 1){
            //超级管理员，有最高权限
            List<SysMenu> menuList = sysMenuService.queryList(new HashMap<String, Object>());
            permsList = new ArrayList<>(menuList.size());
            for(SysMenu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            //非超级管理员，有部分权限
            permsList = sysUserService.queryAllPerms(userId);
        }
        //在权限列表中有很多空
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取前台的用户名
        String username = (String) token.getPrincipal();
        //查询数据库信息
        SysUser user = sysUserService.queryByUserName(username);
        //用户不存在
        if(user == null){
            throw new UnknownAccountException("账号或密码不正确");
        }
        if(user.getIsDelete()==0){
            throw new LockedAccountException("账号已被禁用,请联系管理员");
        }
        //判断密码，交给shiro处理不匹配会抛出IncorrectCredentialsException

        //使用用户名加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //该类会去比较前端密码和数据库密码是否匹配
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,getName());
        return info;
    }
}
