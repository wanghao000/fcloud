package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProviderService providerService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn findAllCompanys(@RequestBody Map<String,Object> map){
        user = ShiroUtil.getUserEntity();
        List<Company> coms = null;
        if(user.getType() == 1){
            coms = companyService.findAllCompanys(map);
        }else if(user.getType() == 2){
            coms = companyService.findComsByProId(user.getProviderId());
        }
        return new TableReturn(coms,coms.size());
    }
    @RequestMapping("/modify/{id}/{isDelete}")
    public R modifyState(@PathVariable("id") int id, @PathVariable("isDelete") int isDelete){
        return companyService.modifyState(id,isDelete)>0? R.ok():R.error();
    }

    @RequestMapping("/providerList")
    public List<Provider> providerList(){
        return providerService.selectIdAndName();
    }

}
