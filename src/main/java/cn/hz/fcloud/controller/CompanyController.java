package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn findAllCompanys(){
        user = ShiroUtil.getUserEntity();
        List<Company> coms = null;
        if(user.getType() == 1){
            coms = companyService.findAllCompanys();
        }else if(user.getType() == 2){
            coms = companyService.findComsByProId(user.getProviderId());
        }
        return new TableReturn(coms,coms.size());
    }

}
