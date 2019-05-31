package cn.hz.fcloud.controller;

import cn.hz.fcloud.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制类
 */
@RestController
public class SysLoginController {

    @RequestMapping(value = "/sys/login",method = RequestMethod.POST)
    public R login(String username,String password){
        //获取shiro用户对象
        Subject subject = SecurityUtils.getSubject();
        //拼接用户令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录动作
        subject.login(token);
        return R.ok();
    }

}
