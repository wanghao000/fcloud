package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ServiceException;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户功能控制器
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private CompanyService companyService;

    /**
     * 当前用户信息
     * @return
     */
    @RequestMapping("/info")
    public R currentInfo(){
        return R.ok().put("user",ShiroUtil.getUserEntity());
    }

    /**
     * 用户表格数据
     * @param map
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public TableReturn list(@RequestBody Map<String,Object> map){
        List<SysUser> userList = sysUserService.queryList(map);
        return new TableReturn(userList,sysUserService.queryListCount(map));
    }

    /**
     * 单个用户信息显示
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("id")Long id){
        SysUser info = sysUserService.selectById(id);
        return R.ok().put("info",info);
    }

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUser user){
        sysUserService.save(user);
        return R.ok();
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
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

    /**
     * 更新一个用户
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUser user){
        int row = sysUserService.update(user);
        if(row != 1){
            return R.error("更新失败");
        }
        return R.ok();
    }

    /**
     * 密码修改
     * @param oldpwd
     * @param newpwd
     * @return
     */
    @RequestMapping("/modify")
    public R modify(String oldpwd,String newpwd){
        SysUser sysUser = ShiroUtil.getUserEntity();
//        SimpleHash pwd = new SimpleHash("MD5",sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getNickname()),1024);
        SimpleHash old = new SimpleHash("MD5",oldpwd, ByteSource.Util.bytes(sysUser.getUsername()),1024);
        if(!sysUser.getPassword().equals(old.toString())){
            throw new ServiceException("原密码输入错误!");
        }
        SysUser user = new SysUser();
        SimpleHash newp = new SimpleHash("MD5",newpwd, ByteSource.Util.bytes(sysUser.getUsername()),1024);
        user.setId(sysUser.getId());
        user.setPassword(newp.toString());
        if(sysUserService.update(user)>0){
            return R.ok();
        }
        return R.error("更新失败!");
    }

    /**
     * 启用一个用户
     * @param id
     * @return
     */
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

    /**
     * 添加用户所需下拉数据
     * @return
     */
    @RequestMapping("/drop")
    public R listDropDown(){
        return R.ok().put("provider",providerService.selectAll()).put("company",companyService.companyList());
    }
}
