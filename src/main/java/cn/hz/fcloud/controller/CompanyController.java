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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        int count = 0;
        if(user.getType() == 1){
            coms = companyService.findAllCompanys(map);
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println(coms.size());
            count = companyService.findAllCompanysCount(map);
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println(count);
        }else if(user.getType() == 2){
            coms = companyService.findComsByProId(map);
            count = companyService.findComsByProIdCount(map);
        }
        return new TableReturn(coms,count);
    }
    @RequestMapping("/modify/{id}/{isDelete}")
    public R modifyState(@PathVariable("id") int id, @PathVariable("isDelete") int isDelete){
        return companyService.modifyState(id,isDelete)>0? R.ok():R.error();
    }

    @RequestMapping("/providerList")
    public List<Provider> providerList(){
        return providerService.selectIdAndName();
    }

    @RequestMapping("/save")
    public void companySave(@ModelAttribute("company") Company company,@RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        System.out.println(company);
//        return 11213123;

    }

}
