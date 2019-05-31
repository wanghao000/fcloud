package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.EquipmentType;
import cn.hz.fcloud.service.EqTypeService;
import cn.hz.fcloud.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/type")
public class EqTypeController {

    @Autowired
    private EqTypeService eqTypeService;
    @RequestMapping("/save")
    public R insertType(@RequestBody EquipmentType equipmentType){
        eqTypeService.insertType(equipmentType);
        return R.ok();
    }
    @RequestMapping("/list")
    public List<EquipmentType> findAAllTypes(){
        return eqTypeService.findAllTypes();
    }
}
