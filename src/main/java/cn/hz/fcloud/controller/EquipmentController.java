package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.EqInfos;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EqInfosService;
import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/eq")
public class EquipmentController {

    @Autowired
    private EquipmentService eqservice;
    @Autowired
    private CompanyService comService;
    @Autowired
    private EqInfosService EqInfosService;
    @Autowired
    private EquipmentDataService eqdataService;

    @RequestMapping("/save")
    public R insertEquipment(@RequestBody Equipment eq){
        SysUser user = ShiroUtil.getUserEntity();
        eq.setCreateTime(new Date());
        eq.setisDelete(1);
        eq.setCreateUser(user.getId().intValue());
        if(eqservice.findOne(eq.getCode()) != null){
            return R.error("编号已存在，请重新输入！");
        }
        eqservice.addEquipment(eq);
        return R.ok();
    }

    @RequestMapping("/list")
    public TableReturn findAllEquiments(@RequestBody Map<String,Object> map ){
        SysUser user = ShiroUtil.getUserEntity();
        List<EqInfos> eqs = null;
        int count = 0;
        switch (user.getType()){
            case 1 : eqs = EqInfosService.findAll(map);count = EqInfosService.findAllCount(map);break;
            case 2 : map.put("id",user.getProviderId());eqs = EqInfosService.findByProviderId(map);count = EqInfosService.findByProviderIdCount(map);break;
            case 3 : map.put("id",user.getCompanyId());eqs = EqInfosService.findByComId(map);count = EqInfosService.findByComIdCount(map);break;
        }

        return new TableReturn(eqs,count);
    }

    @RequestMapping("/find/{code}")
    public EqInfos editEquiment(@PathVariable("code") String code){
        return EqInfosService.findOne(code);
    }

    @RequestMapping("/companyList")
    public List<Company> companyList(){
        List<Company> coms= comService.companyList();
        return coms;
    }

    @RequestMapping("/delete/{code}")
    public R delEq(@PathVariable("code")String code){
        return eqservice.delEquipment(code)>0?R.ok():R.error();
    }

    @RequestMapping("/update")
    public R updateEq(@RequestBody Equipment eq){
        return eqservice.updateEq(eq)>0?R.ok():R.error();
    }
	@RequestMapping("/modify/{code}/{isDelete}")
    public R modifyState(@PathVariable("code") String code,@PathVariable("isDelete") int isDelete){
        return  eqservice.modifyState(code,isDelete)>0?R.ok():R.error();
    }

    @RequestMapping("/lineNum")
    @ResponseBody
    public Map<String, Object> lineNum(){
        SysUser user = ShiroUtil.getUserEntity();
        Map<String, Object> lineNum = new HashMap<>();
        int lineCount = 0;
        int online = 0;
        if(user.getType() == 1) {
            lineCount = eqservice.countAll();
            online = lineCount - eqservice.lineCount();
        } else if(user.getType() == 2) {
            List<Company> companyList = comService.getCompanyListByProviderId(user.getProviderId());
            for (Company company : companyList) {
                List<Equipment> equipmentList = eqservice.getEquipmentList(company.getId());
                lineCount += equipmentList.size();
                for (Equipment equipment : equipmentList) {
                    if(equipment.getIsOnline() == 1) {
                        online++;
                    }
                }
            }
        }
        lineNum.put("lineCount", lineCount);
        lineNum.put("online", online);
        return lineNum;
    }
	
	@RequestMapping("/typeAndCount")
    @ResponseBody
    public Map<String, Object> typeAndCount(){
        Map<String, Object> tac = new HashMap<>();
        SysUser user = ShiroUtil.getUserEntity();
        List<Map<String, Object>> typeAndCount;
        List<Object> name = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        if(user.getType() == 1) {
            typeAndCount = eqservice.findTypeAndCount();
        } else {
            typeAndCount = eqservice.findTypeAndCountByUser(user.getId());
        }
        for (Map<String, Object> map : typeAndCount) {
            if("0".equals(String.valueOf(map.get("type")))) {
                name.add("无线烟感");
                count.add(map.get("ct"));
            }
        }
        tac.put("name", name);
        tac.put("count", count);
        return tac;
	}

    @RequestMapping("/alarmEquipmentAndCount")
    @ResponseBody
    public Map<String, Object> alarmEquipmentAndCount(){
        Map<String, Object> aec = new HashMap<>();
        List<Object> name = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        List<Map<String, Object>> alarmEquipmentAndCount;
        SysUser user = ShiroUtil.getUserEntity();
        if (user.getType() == 1) {
            alarmEquipmentAndCount = eqdataService.findAlarmEquipmentAndCount();
        } else {
            alarmEquipmentAndCount = eqdataService.findAlarmEquipmentAndCountByUser(user.getId());
        }
        for (Map<String, Object> map : alarmEquipmentAndCount) {
            if("0".equals(String.valueOf(map.get("type")))) {
                name.add("无线烟感");
                count.add(map.get("ct"));
            }
        }
        aec.put("name", name);
        aec.put("count", count);
        return aec;
    }

    @RequestMapping("/findCompanyAndCount")
    @ResponseBody
    public Map<String, Object> findCompanyAndCount(){
        Map<String, Object> cc = new HashMap<>();
        List<Object> name = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        List<Map<String, Object>> companyAndCount;
        SysUser user = ShiroUtil.getUserEntity();
        if(user.getType() == 1) {
            companyAndCount = eqservice.findCompanyAndCount();
        } else {
            companyAndCount = eqservice.findCompanyAndCountByUser(user.getId());
        }
        for (Map<String, Object> map : companyAndCount) {
            name.add(map.get("name"));
            count.add(map.get("ci"));
        }
        cc.put("name", name);
        cc.put("count", count);
        return cc;
    }

    @RequestMapping("findCompanyAlarmCount")
    @ResponseBody
    public Map<String, Object> findCompanyAlarmCount(){
        Map<String, Object> cac = new HashMap<>();
        List<Object> name = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        List<Map<String, Object>> companyAlarmCount = eqdataService.findCompanyAlarmCount();
        for (Map<String, Object> map : companyAlarmCount) {
            name.add(map.get("name"));
            count.add(map.get("cn"));
        }
        cac.put("name", name);
        cac.put("count", count);
        return cac;
    }

    @RequestMapping("find7dayAlarmCount")
    @ResponseBody
    public Map<String, Object> find7dayAlarmCount(){
        Map<String, Object> cac = new HashMap<>();
        List<Object> date = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        List<Map<String, Object>> companyAlarmCount = eqdataService.find7dayAlarmCount();
        for (Map<String, Object> map : companyAlarmCount) {
            date.add(map.get("dfc"));
            count.add(map.get("cc"));
        }
        cac.put("date", date);
        cac.put("count", count);
        return cac;
    }

    @RequestMapping("recent5Record")
    @ResponseBody
    public List<Map<String, Object>> recent5Record(){
        List<Map<String, Object>> companyAlarmCount = eqdataService.recent5Record();
        for (Map<String, Object> map : companyAlarmCount) {
            System.out.println(map);
        }
        return companyAlarmCount;
    }
}
