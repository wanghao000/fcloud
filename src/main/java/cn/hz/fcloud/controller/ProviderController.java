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
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthStyle;
import java.util.*;

@RestController
@RequestMapping("/sys/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private CompanyService companyService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn providerList(@RequestBody Map<String,Object> map){
        List<Provider> pros = providerService.findAllProvider(map);
        return new TableReturn(pros,providerService.findAllProviderCount(map));
    }
    @RequestMapping("/save")
    @RequiresPermissions("sys:provider:save")
    public R insertProvider(@RequestBody Provider provider){
        user = ShiroUtil.getUserEntity();
        provider.setCreateTime(new Date());
        provider.setIsDelete(1);
        provider.setCreateUser(user.getId());
        return providerService.insert(provider)>0? R.ok():R.error();
    }

    @RequestMapping("/modify/{id}/{isDelete}")
    @RequiresPermissions("sys:provider:delete")
    public R modifyState(@PathVariable("id") int id,@PathVariable("isDelete") int isDelete){
        return providerService.modifyState(id,isDelete)>0?R.ok():R.error();
    }
    @RequestMapping("/find/{id}")
    public Provider findProvider(@PathVariable("id") Long id){
        return providerService.getProviderById(id);
    }

    @RequestMapping("/update")
    @RequiresPermissions("sys:provider:update")
    public R updateProvider(@RequestBody Provider provider){
        return providerService.updateByPrimaryKeySelective(provider)>0?R.ok():R.error();
    }

    @RequestMapping("/ztree")
    public R ztree(){
        SysUser user = ShiroUtil.getUserEntity();

        if(user.getType()==1) {
            List<Provider> list = providerService.selectAll();
            for (Provider p : list) {
                p.setChildren(companyService.selectComsByProId(p.getId()));
            }
            return R.ok().put("data", JSONArray.toJSON(list));
        }
        if(user.getType()==2){
            Provider provider = providerService.getProviderById(user.getProviderId());
            provider.setChildren(companyService.selectComsByProId(provider.getId()));
            return R.ok().put("data", JSONArray.toJSON(provider));
        }

        if(user.getType()==3){
            Company company = companyService.getCompanyById(user.getCompanyId());
            Provider provider = providerService.getProviderById(company.getId());
            List<Company> list = new ArrayList<>();
            list.add(company);
            provider.setChildren(list);
            return R.ok().put("data", JSONArray.toJSON(provider));
        }
        return null;
    }
}
