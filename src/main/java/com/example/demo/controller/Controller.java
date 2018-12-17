package com.example.demo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@ResponseBody
	@RequestMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}

	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("host", "https://www.baidu.com");
		return "index";
	}
}
