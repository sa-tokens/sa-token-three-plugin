package com.pj;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sa-Token 整合 sa-token-three-token-prefix-compatible-cookie 示例：使 token-prefix 兼容 Cookie 鉴权模式
 * @author yigetao
 *
 */
@SpringBootApplication
public class SaTokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaTokenDemoApplication.class, args); 
		System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}
	
}
