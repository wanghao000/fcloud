package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Equipment;
import cn.hz.fcloud.service.EquipmentService;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.TableReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sys/eq")
public class EquipmentController {

    @Autowired
    private EquipmentService eqservice;

    @RequestMapping("/save")
    public R insertEquipment(Equipment eq){
        eqservice.addEquipment(eq);
        return R.ok();
    }

    @RequestMapping("/list")
    public TableReturn findAllEquiments(){
        List<Equipment> eqs = eqservice.findAll();
        return new TableReturn(eqs,eqs.size());
    }

    @RequestMapping("/update")
    public R editEquiment(Equipment eq){
        return R.ok();
    }
}
