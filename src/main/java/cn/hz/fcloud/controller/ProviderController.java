package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/list")
    public Map<Object, Integer> providerList(){
        Map<Object, Integer> map = new HashMap<>();
        SysUser user = ShiroUtil.getUserEntity();
        List<Provider> list = providerService.selectByCreateUser(user.getCreateUser());
        for(Provider pro : list){
            int count = 0 ;
            List<Company> companyList = companyService.getCompanyListByProviderId(pro.getId());
            for(Company com : companyList){
                count += equipmentService.getEquipmentList(com.getId()).size();
            }
            map.put(pro,count);
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(map.size());
        return map;
    }
}
