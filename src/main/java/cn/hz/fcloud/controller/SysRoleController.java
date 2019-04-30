package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.entity.pojo.RolePOJO;
import cn.hz.fcloud.service.SysRoleMenuService;
import cn.hz.fcloud.service.SysRoleService;
import cn.hz.fcloud.service.SysUserRoleService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public TableReturn list(@RequestBody Map<String,Object> map){
        //查询列表数据
        List<SysRole> list = sysRoleService.queryList(map);
        int total = sysRoleService.queryTotal(map);
        return new TableReturn(list,total);
    }
    @RequestMapping("/authorization/{id}")
    public R authorization(@PathVariable("id")long id){
        //查询已经拥有角色
        List<RolePOJO> roleList = sysUserRoleService.haveRole(id);
        //查询除了拥有以外角色
        List<SysRole> all = sysRoleService.getNotIn(roleList);
        return R.ok().put("roleList",roleList).put("all",all);
    }
    @RequestMapping("/auth/{id}")
    public R auth(@PathVariable("id")long id,@RequestBody Long[] roleIdList){
        sysUserRoleService.saveOrUpdate(id,Arrays.asList(roleIdList));
        System.out.println(id+":"+roleIdList);
        return R.ok();
    }
    /**
     * 保存角色
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRole role){
        if(StringUtils.isBlank(role.getRoleName())){
            return R.error("角色名称不能为空");
        }

        sysRoleService.save(role);

        return R.ok();
    }
    /**
     * 删除角色
     */
    @RequestMapping("/delete/{roleId}")
    @RequiresPermissions("sys:role:delete")
    public R delete(@PathVariable("roleId")long roleId){
        sysRoleService.delete(roleId);
        return R.ok();
    }
    /**
     * 修改角色
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysRole role){
        if(StringUtils.isBlank(role.getRoleName())){
            return R.error("角色名称不能为空");
        }

        sysRoleService.update(role);

        return R.ok();
    }
    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") Long roleId){
        SysRole role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok().put("role", role);
    }
}
