package com.pj.test;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录测试
 * @author RockMan
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

    // 测试权限校验  ---- http://localhost:8081/acc/hasPermission
    @RequestMapping("hasPermission")
    public SaResult hasPermission() {
        System.out.println(StpUtil.hasPermission("user:add"));
        System.out.println(StpUtil.hasPermission(10044, "user:add"));

        StpUtil.checkPermission("user:1");
        StpUtil.checkPermissionOr("user:2", "user:3");
        StpUtil.checkPermissionAnd("user:4", "user:5");

        return SaResult.ok();
    }

    // 测试角色校验  ---- http://localhost:8081/acc/hasRole
    @RequestMapping("hasRole")
    public SaResult hasRole() {
        System.out.println(StpUtil.hasRole("ceo"));
        System.out.println(StpUtil.hasRole(10044, "ceo"));

        StpUtil.checkRole("ceo:1");
        StpUtil.checkRoleOr("ceo:2", "ceo:3");
        StpUtil.checkRoleAnd("ceo:4", "ceo:5");

        return SaResult.ok();
    }

}
