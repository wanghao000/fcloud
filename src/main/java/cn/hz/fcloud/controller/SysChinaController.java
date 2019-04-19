package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.SysChina;
import cn.hz.fcloud.service.SysChinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/china")
public class SysChinaController {

    @Autowired
    private SysChinaService service;

    @RequestMapping("/show/{id}")
    public List<SysChina> getAddress(@PathVariable("id") int id){
        return service.selectByParentid(id);
    }
}
