package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.entity.EqInfos;
import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.CompanyService;
import cn.hz.fcloud.service.EqInfosService;
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

    @RequestMapping("/save")
    public R insertEquipment(@RequestBody Equipment eq){
        eq.setCreateTime(new Date());
        eq.setisDelete(1);
        if(eqservice.findOne(eq.getCode()) != null){
            return R.error("编号已存在，请重新输入！");
        }
        eqservice.addEquipment(eq);
        return R.ok();
    }

    @RequestMapping("/list")
    public TableReturn findAllEquiments(){
        SysUser user = ShiroUtil.getUserEntity();
        List<EqInfos> eqs = EqInfosService.findByComId(user.getId());
        return new TableReturn(eqs,eqs.size());
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
        } else if(user.getType() == 3) {
            List<Equipment> equipmentList = eqservice.getEquipmentList(user.getCompanyId());
            lineCount = equipmentList.size();
            for (Equipment equipment : equipmentList) {
                if(equipment.getIsOnline() == 1) {
                    online++;
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
        List<Map<String, Object>> typeAndCount = eqservice.findTypeAndCount();
        System.out.println(typeAndCount);
        List<Object> name = new ArrayList<>();
        List<Object> count = new ArrayList<>();
        for (Map<String, Object> map : typeAndCount) {
            if(0 == Integer.parseInt(map.get("type")+"")) {
                name.add("无线烟感");
                count.add((int) map.get("ct"));
            }
        }
        tac.put("name", name);
        tac.put("count", count);
        System.out.println(tac);
        return tac;
	}
}
