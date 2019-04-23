package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysMenu;
import cn.hz.fcloud.service.SysMenuService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public R user(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(ShiroUtil.getUserEntity().getId());

        return R.ok().put("menuList", menuList);
    }
    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public R perms(){
        //查询列表数据
       List<SysMenu> menuList = sysMenuService.queryList(null);

        return R.ok().put("menuList", menuList);
    }
}
