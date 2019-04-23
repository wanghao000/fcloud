package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/info")
    public R currentInfo(){
        return R.ok().put("user",ShiroUtil.getUserEntity());
    }
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public TableReturn list(@RequestBody Map<String,Object> map){
        List<SysUser> userList = sysUserService.queryList(map);
        return new TableReturn(userList,sysUserService.queryListCount(map));
    }
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("id")Long id){
        SysUser info = sysUserService.selectById(id);
        return R.ok().put("info",info);
    }
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUser user){
        int row = sysUserService.save(user);
        if(row != 1){
            return R.error("添加失败");
        }
        return R.ok();
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("sys:user:delete")
    public R delete(@PathVariable("id")Long id){
        SysUser user = new SysUser();
        user.setId(id);
        user.setIsDelete(0);
        int row = sysUserService.update(user);
        if(row != 1){
            return R.error("删除失败");
        }
        return R.ok();
    }
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUser user){
        int row = sysUserService.update(user);
        if(row != 1){
            return R.error("更新失败");
        }
        return R.ok();
    }

    @RequestMapping("/use/{id}")
    public R use(@PathVariable("id")Long id){
        SysUser user = new SysUser();
        user.setId(id);
        user.setIsDelete(1);
        int row = sysUserService.update(user);
        if(row != 1){
            return R.error("启用失败");
        }
        return R.ok();
    }

    @RequestMapping("/drop")
    public R listDropDown(){
        return R.ok().put("provider",providerService.selectAll()).put("company",companyService.companyList());
    }
}
