package com.pj;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.dev33.satoken.three.example.SaExampleUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sa-Token 整合 sa-token-three-example-plugin 示例
 * @author click33
 *
 */
@SpringBootApplication
public class SaTokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaTokenDemoApplication.class, args); 
		System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
		// 调用插件里的工具类方法
		SaExampleUtil.show();
		// 调用 Sa-Token 框架 API 将执行自定义的组件逻辑
		SaTempUtil.createToken(1001, 1000);
	}
	
}
