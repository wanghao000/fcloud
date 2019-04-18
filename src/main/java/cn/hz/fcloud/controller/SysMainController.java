package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.*;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.service.ProviderService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/main")
public class SysMainController {
    @Autowired
    private EquipmentDataService equipmentDataService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/info")
    public R info(){
        SysUser user = ShiroUtil.getUserEntity();
        Map<String,Object> map = new HashMap<>();
        //超级管理员或者运维人员
        if(user.getType()==1){
            //今日告警总数
            int alarmCount = equipmentDataService.getAlarmCount();
            //离线总数
            int lineCount = equipmentService.lineCount();
            //接入企业
            int companyCount = companyService.countAll();
            //接入设备
            int equipmentCount = equipmentService.countAll();
            map.put("alarmCount",alarmCount);
            map.put("lineCount",lineCount);
            map.put("companyCount",companyCount);
            map.put("equipmentCount",equipmentCount);
        }
        //运营商
        if(user.getType()==2){
            //查询账号下有哪些企业
            List<Company> companyList = companyService.getCompanyListByProviderId(user.getProviderId());
            //查询每个企业下的设备
            List<Equipment> equipmentList = new ArrayList<>();
            for(Company company:companyList){
                equipmentList.addAll(equipmentService.getEquipmentList(company.getId()));
            }
            int alarmCount = 0;
            int lineCount = 0;
            for(Equipment equipment:equipmentList){
                alarmCount+=equipmentDataService.geetAlarmCountByCode(equipment.getCode());
                if(equipment.getIsOnline()==0){
                    lineCount++;
                }
            }
            map.put("alarmCount",alarmCount);
            map.put("lineCount",lineCount);
            map.put("companyCount",companyList.size());
            map.put("equipmentCount",equipmentList.size());
        }
        //企业用户
        if(user.getType()==3){
            //获取企业信息
            Company company = companyService.getCompanyById(user.getCompanyId());
            map.put("company",company);
            //获取服务商信息
            Provider provider = providerService.getProviderById(company.getProviderId());
            map.put("provider",provider);
            //企业下设备数
            List<Equipment> equipmentList  = equipmentService.getEquipmentList(company.getId());
            int equipmentCount = equipmentList.size();
            int alarmCount = 0;
            int lineCount = 0;
            List<String> codes = new ArrayList<>();
            for(Equipment equipment:equipmentList){
                codes.add(equipment.getCode());
                alarmCount+=equipmentDataService.geetAlarmCountByCode(equipment.getCode());
                if(equipment.getIsOnline()==0){
                    lineCount++;
                }
            }
            map.put("equipmentCount",equipmentCount);
            map.put("alarmCount",alarmCount);
            map.put("lineCount",lineCount);
            List<EquipmentDataAndName> dataList = equipmentDataService.getAlarmList(codes);
            map.put("dataList",dataList);
        }
        return R.ok().put("info",map);
    }
}
