package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysMenu;
import cn.hz.fcloud.service.SysMenuService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public TableReturn list(@RequestBody Map<String,Object>  map){

        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryList(map);
        int total = sysMenuService.queryTotal();

        return new TableReturn(menuList,total);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenu menu){

        sysMenuService.save(menu);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody Long[] menuIds){
        /*for(Long menuId : menuIds){
            if(menuId.longValue() <= 28){
                return R.error("系统菜单，不能删除");
            }
        }*/
        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenu menu){

        sysMenuService.update(menu);

        return R.ok();
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId){
        SysMenu menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }
}
