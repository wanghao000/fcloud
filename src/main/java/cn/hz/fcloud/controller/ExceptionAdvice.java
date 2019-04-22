package cn.hz.fcloud.controller;

import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ServiceException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public R exceptionHandler(Exception e){

        if(e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException){
            logger.error(e.getMessage());
           return R.error("账号或密码不正确");
        }
        if(e instanceof LockedAccountException){
            logger.error(e.getMessage());
            return R.error(e.getMessage());
        }
        if(e instanceof UnauthorizedException){
            logger.error(e.getMessage());
            return R.error("该账号未授权无法操作");
        }
        if(e instanceof ServiceException){
            logger.error(e.getMessage());
            return R.error("业务异常："+e.getMessage());
        }
        logger.error(e.getMessage());
        return R.error("未知的异常:"+e.getMessage());
    }
}
