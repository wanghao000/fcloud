package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn providerList(){
        user = ShiroUtil.getUserEntity();
        List<Provider> pros = providerService.selectByCreateUser(user.getId());
        return new TableReturn(pros,pros.size());
    }
    @RequestMapping("/save")
    public R insertProvider(@RequestBody Provider provider){
        user = ShiroUtil.getUserEntity();
        provider.setCreateTime(new Date());
        provider.setIsDelete(1);
        provider.setCreateUser(user.getIsDelete());
        return providerService.insert(provider)>0? R.ok():R.error("添加失败！请重新添加！");
    }
}
