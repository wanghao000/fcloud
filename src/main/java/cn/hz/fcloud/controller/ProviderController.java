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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Provider> pros = providerService.findAllProvider();
        return new TableReturn(pros,pros.size());
    }
    @RequestMapping("/save")
    public R insertProvider(@RequestBody Provider provider){
        user = ShiroUtil.getUserEntity();
        provider.setCreateTime(new Date());
        provider.setIsDelete(1);
        provider.setCreateUser(user.getIsDelete());
        return providerService.insert(provider)>0? R.ok():R.error();
    }

    @RequestMapping("/modify/{id}/{isDelete}")
    public R modifyState(@PathVariable("id") int id,@PathVariable("isDelete") int isDelete){
        return providerService.modifyState(id,isDelete)>0?R.ok():R.error();
    }
    @RequestMapping("/find/{id}")
    public Provider findProvider(@PathVariable("id") Long id){
        return providerService.getProviderById(id);
    }
    @RequestMapping("/update")
    public R updateProvider(@RequestBody Provider provider){
        return providerService.updateByPrimaryKeySelective(provider)>0?R.ok():R.error();
    }
}
