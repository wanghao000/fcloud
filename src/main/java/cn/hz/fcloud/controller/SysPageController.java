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
	@RequestMapping("/sys/{parent}/{url}.vm")
	public String page1(@PathVariable("parent") String parent,@PathVariable("url") String url){
		return "sys/"+parent+"/" + url ;
	}
}
