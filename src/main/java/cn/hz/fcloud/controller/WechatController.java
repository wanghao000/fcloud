package cn.hz.fcloud.controller;

import cn.hz.fcloud.entity.Eq;
import cn.hz.fcloud.service.EqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private EqService eqService;

    @RequestMapping("/eq/list")
    public List<Eq> findAllEqs(){
        return eqService.findAllEqs();
    }
}
