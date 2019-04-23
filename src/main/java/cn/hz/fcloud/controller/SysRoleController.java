package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysRole;
import cn.hz.fcloud.service.SysRoleService;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
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
}
