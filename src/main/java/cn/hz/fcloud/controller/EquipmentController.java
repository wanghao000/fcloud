package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.*;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EqInfosService;
import cn.hz.fcloud.service.EquipmentDataService;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import cn.hz.fcloud.utils.TableReturn;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("sys:device:save")
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
    @RequestMapping("/list/{companyId}")
    public TableReturn findAllEquiments(@RequestBody Map<String,Object> map,@PathVariable("companyId")Long companyId ){
        map.put("id",companyId);
        List<EqInfos> eqs = EqInfosService.findByComId(map);
        int count = EqInfosService.findByComIdCount(map);
        return new TableReturn(eqs,count);
    }
    @RequestMapping("/find/{code}")
    public EqInfos editEquiment(@PathVariable("code") String code){
        return EqInfosService.findOne(code);
    }

    @RequestMapping("/companyList")
    public List<Company> companyList(){
        SysUser user = ShiroUtil.getUserEntity();
        List<Company> coms = new ArrayList<>();
        switch (user.getType()){
            case 1 : coms = comService.companyList();break;
            case 2 : coms = comService.selectComsByProId(user.getProviderId());break;
            case 3 : coms.add(comService.getCompanyById(user.getCompanyId()));break;
        }
        return coms;
    }

    @RequestMapping("/delete/{code}")
    @RequiresPermissions("sys:company:delete")
    public R delEq(@PathVariable("code")String code){
        return eqservice.delEquipment(code)>0?R.ok():R.error();
    }

    @RequestMapping("/update")
    @RequiresPermissions("sys:device:update")
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
        SysUser user = ShiroUtil.getUserEntity();
        List<Map<String, Object>> companyAlarmCount;
        if(user.getType() == 1) {
            companyAlarmCount = eqdataService.findCompanyAlarmCount();
        } else {
            companyAlarmCount = eqdataService.findCompanyAlarmCountByUser(user.getId());
        }
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
        SysUser user = ShiroUtil.getUserEntity();
        List<Map<String, Object>> companyAlarmCount;
        if(user.getType() == 1) {
            companyAlarmCount = eqdataService.find7dayAlarmCount();
        } else {
            companyAlarmCount = eqdataService.find7dayAlarmCountByUser(user.getId());
        }
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
        SysUser user = ShiroUtil.getUserEntity();
        return user.getType() == 1?eqdataService.recent5Record():eqdataService.recent5RecordByUser(user.getId());
    }

    @RequestMapping("findAreaAlarmCount")
    @ResponseBody
    public List<Map<String, Object>> findAreaAlarmCount(){
        SysUser user = ShiroUtil.getUserEntity();
        return user.getType() == 1?eqdataService.findAreaAlarmCount():eqdataService.findAreaAlarmCountByUser(user.getId());
    }

    @ResponseBody
    @RequestMapping("/info")
    public TableReturn info(@RequestBody Map<String,Object> map){
        List<EquipmentData> data = eqdataService.getInfoByCode(map);
        return new TableReturn(data,eqdataService.getInfoCount(map));
    }

    @RequestMapping("/znfx")
    public R znfx(){
        //查出该账号所属哪个公司Id
        SysUser user = ShiroUtil.getUserEntity();
        long companyId = user.getCompanyId();
        List<Equipment> equipmentList = eqservice.getEquipmentList(companyId);
        List<Equipment> alarmList = alarmDevice(equipmentList);
        return R.ok().put("jkd",equipmentList.size()).put("yhd",alarmList.size()).put("data",new TableReturn(alarmList,alarmList.size()));
    }

    //查询报警设备
    public List<Equipment> alarmDevice(List<Equipment> equipmentList){
        List<Equipment> datas = new ArrayList<>();
        for(Equipment eq:equipmentList){
            if(eqdataService.geetAlarmCountByCode(eq.getCode())>0){
                datas.add(eq);
            }
        }
        return datas;
    }
}
