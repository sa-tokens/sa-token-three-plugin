package com.pj.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.util.SaResult;

/**
 * 测试专用Controller
 */
@RestController
@RequestMapping("/test/")
public class TestController {

	// 测试   浏览器访问： http://localhost:8081/test/test
	@RequestMapping("test")
	public SaResult test() {
		return SaResult.ok();
	}
	
	// 测试   浏览器访问： http://localhost:8081/test/test2
	@RequestMapping("test2")
	public SaResult test2() {
		return SaResult.ok();
	}

}
