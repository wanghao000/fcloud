package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.*;
import cn.hz.fcloud.service.*;
import cn.hz.fcloud.utils.FileUpUtil;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@RestController
@RequestMapping("/sys/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyInfosService companyInfosService;
    @Autowired
    private EqService eqService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn findAllCompanys(@RequestBody Map<String,Object> map){
        user = ShiroUtil.getUserEntity();
        List<Company> coms = null;
        int count = 0;
        if(user.getType() == 1){
            coms = companyService.findAllCompanys(map);
            count = companyService.findAllCompanysCount(map);
        }else if(user.getType() == 2){
            map.put("id",user.getProviderId());
            coms = companyService.findComsByProId(map);
            count = companyService.findComsByProIdCount(map);
        }
        return new TableReturn(coms,count);
    }
    @RequestMapping("/modify/{id}/{isDelete}")
    @RequiresPermissions("sys:company:delete")
    public R modifyState(@PathVariable("id") int id, @PathVariable("isDelete") int isDelete){
        return companyService.modifyState(id,isDelete)>0? R.ok():R.error();
    }

    @RequestMapping("/providerList")
    public List<Provider> providerList(){
        user = ShiroUtil.getUserEntity();
        List<Provider> providers = new ArrayList<Provider>();
        if(user.getType() == 1){
            providers = providerService.selectIdAndName();
        }else if(user.getType() == 2){
            Provider provider = providerService.getProviderById(user.getProviderId());
            providers.add(provider);
        }
        return providers;
    }

    @RequestMapping("/save")
    @RequiresPermissions("sys:company:save")
    public R companySave(MultipartFile file,String company){
        Company comEntity = JSON.parseObject(company,Company.class);
        //存储返回信息
        Map<String,Object> map = new HashMap<String, Object>();
        //判断用户名是否存在 true表示存在
        Boolean is_exist = null != companyService.findCompanyByName(comEntity.getName());
        if(!is_exist){
            String name = FileUpUtil.fileUp(file);
            comEntity.setCreateUser(user.getId().intValue());
            comEntity.setCreateTime(new Date());
            comEntity.setIsDelete(1);
            comEntity.setPicture(name);
            String code = companyService.findCompanyCode();
            String newCode = "";
            if(code != "" && code != null){
                int temp = Integer.valueOf(code.split("A")[1])+1;
                String s = String.format("%05d", temp);
                newCode = "A"+s;
            }else{
                newCode = "A00001";
            }
            comEntity.setCode(newCode);
            companyService.insert(comEntity);
            SysUser newUser = new SysUser();
            newUser.setUsername(newCode);
            newUser.setIsDelete(1);
            newUser.setCreateTime(new Date());
            newUser.setCreateUser(user.getId());
            newUser.setPassword("000000");
            newUser.setCompanyId(comEntity.getId());
            newUser.setType(3);
            newUser.setNickname(comEntity.getName());
            newUser.setProviderId(comEntity.getProviderId());
            sysUserService.save(newUser);
            map.put("code",newCode);
            map.put("pwd","000000");
            map.put("result","success");
            return R.ok(map);
        }else{
            return R.error("公司名已存在");
        }
    }
    @RequestMapping("/find/{id}")
    public CompanyInfos find(@PathVariable("id")int id){
        return companyInfosService.findCompanyInfos(id);
    }
    @RequestMapping("/update")
    @RequiresPermissions("sys:company:update")
    public R update(MultipartFile file,String company){
        Company comEntity = JSON.parseObject(company,Company.class);
        System.out.println("***************************************");
        System.out.println(comEntity);
        Company exist_company = companyService.findCompanyByName(comEntity.getName());
        //判断要修改的用户名是否存在 true表示存在
        Boolean is_exist = exist_company != null && exist_company.getId() != comEntity.getId();
        if(!is_exist){
            String name = FileUpUtil.fileUp(file);
            comEntity.setPicture(name);
            companyService.updateByPrimaryKeySelective(comEntity);
            return R.ok();
        }else{
            return R.error("公司名已存在");
        }
    }

    @RequestMapping("/monitor")
    public List<Eq> monitor(){
        user = ShiroUtil.getUserEntity();
        return eqService.findByComId(user.getCompanyId());
    }
}
