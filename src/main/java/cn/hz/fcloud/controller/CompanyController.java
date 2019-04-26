package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import com.alibaba.fastjson.JSON;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

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
            map.put("id",user.getProviderId());
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
    public String companySave(MultipartFile file,String company){
        String name = "";
        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
//            Properties prop = new Properties();
            try{
                Resource r = new ClassPathResource("/file.properties");
                Properties prop = new Properties();
                prop.load(new FileInputStream(r.getFile()));
//                InputStream input = new BufferedInputStream(new FileInputStream("/file.properties"));
//                prop.load(input);
                String realPath = prop.getProperty("dir");
                System.out.println(realPath);
                name = realPath+UUID.randomUUID()+"."+originalFilename.split("\\.")[1];
                File files = new File(realPath);
                if(!files.exists()){
                    files.mkdirs();
                }
                File saveFile = new File(files, name);
                file.transferTo(saveFile);
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        Company comEntity = JSON.parseObject(company,Company.class);
        comEntity.setCreateUser(user.getId().intValue());
        comEntity.setCreateTime(new Date());
        comEntity.setIsDelete(1);
        comEntity.setPicture(name);
        String code = companyService.findCompanyCode();
        String newCode = "";
        if(code != "" && code != null){
            int temp = Integer.valueOf(code.split("A")[1]);
            String s = String.format("%05d", temp);
            newCode = "A"+s;
        }else{
            newCode = "A00001";
        }
        comEntity.setCode(newCode);
        System.out.println(comEntity);
        return "12316554";

    }
}
