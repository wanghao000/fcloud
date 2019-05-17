package cn.hz.fcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 */
@Controller
public class SysPageController {
	
	@RequestMapping("/sys/{url}.vm")
	public String page1(@PathVariable("url") String url){
		return "sys/" + url ;
	}

	@RequestMapping("/nvms/{url}")
	public String page2(@PathVariable("url") String url){
		return "nvms/" + url ;
	}
}
