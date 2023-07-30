package com.pj.test;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试
 * @author yigetao
 */
@RestController
@RequestMapping("/acc/")
public class LoginController {

    // 测试登录  ---- http://localhost:8081/acc/doLogin?id=10001
    @RequestMapping("doLogin")
    public SaResult doLogin(@RequestParam(defaultValue = "10001") long id) {
        StpUtil.login(id);
        return SaResult.ok();
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 校验登录状态  ---- http://localhost:8081/acc/checkLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin() {
        StpUtil.checkLogin();
        return SaResult.ok("校验登录成功");
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}
