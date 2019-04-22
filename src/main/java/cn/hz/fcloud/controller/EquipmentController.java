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
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

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
}
