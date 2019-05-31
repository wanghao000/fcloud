package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.SerialNumberUtil;
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
    @Autowired
    private SysUserService sysUserService;

    private SysUser user;

    @RequestMapping("/list")
    public TableReturn providerList(@RequestBody Map<String,Object> map){
        List<Provider> pros = providerService.findAllProvider(map);
        return new TableReturn(pros,providerService.findAllProviderCount(map));
    }
    @RequestMapping("/save")
    @RequiresPermissions("sys:provider:save")
    public R insertProvider(@RequestBody Provider provider){
        //判断服务商名是否存在 true表示存在
        Boolean is_exist = null != providerService.findProviderByName(provider.getName());
        if(!is_exist){
            Map<String,Object> map = new HashMap<String, Object>();
            user = ShiroUtil.getUserEntity();
            provider.setCreateTime(new Date());
            provider.setIsDelete(1);
            provider.setCreateUser(user.getId());
            String code = providerService.findProviderCode();
//            String newCode = "";
//            if(code != "" && code != null){
//                int temp = Integer.valueOf(code.split("B")[1])+1;
//                String s = String.format("%05d", temp);
//                newCode = "B"+s;
//            }else{
//                newCode = "B00001";
//            }
//            provider.setCode(newCode);
            String newCode = SerialNumberUtil.generateNumner();
            //判断用户名是否存在 true表示存在
            Boolean code_is_exist  = null != sysUserService.queryByUserName(newCode);
            while (code_is_exist){
                newCode = SerialNumberUtil.generateNumner();
                code_is_exist  = null != sysUserService.queryByUserName(newCode);
            }
            provider.setCode(newCode);
            providerService.insert(provider);
            SysUser newUser = new SysUser();
            newUser.setUsername(newCode);
            newUser.setIsDelete(1);
            newUser.setCreateTime(new Date());
            newUser.setCreateUser(user.getId());
            newUser.setPassword("000000");
            newUser.setType(2);
            newUser.setNickname(provider.getName());
            newUser.setProviderId(provider.getId());
            sysUserService.save(newUser);
            map.put("code",newCode);
            map.put("pwd","000000");
            map.put("result","success");
            return R.ok(map);
        }else{
            return R.error("该服务商名已存在!");
        }
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
    @RequestMapping("/pRanking/{type}")
    public R ranking(@PathVariable("type")int type){
        List<Map<String,Object>> data = providerService.getProviderRanking(type);
        return R.ok().put("data",data);
    }
    @RequestMapping("/cRanking")
    public R cranking(){
        List<Map<String,Object>> data = providerService.getCompanyRanking();
        return R.ok().put("data",data);
    }
}
