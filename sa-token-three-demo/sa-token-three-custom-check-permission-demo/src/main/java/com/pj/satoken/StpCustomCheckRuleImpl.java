package com.pj.satoken;

import cn.dev33.satoken.three.custom.check.permission.StpCustomCheckRule;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 自定义权限验证规则接口，通过实现此接口并注入容器，来自定义权限验证规则
 *
 * @author RockMan
 */
@Component
public class StpCustomCheckRuleImpl implements StpCustomCheckRule {

    @Override
    public boolean hasPermission(Object loginId, String permission) {
        System.out.println("------ 自定义权限校验规则：" + loginId + " 是否拥有权限 " + permission);
        return Arrays.asList("user:add", "user:1", "user:2", "user:3", "user:4", "user:5").contains(permission);
    }

    @Override
    public boolean hasRole(Object loginId, String role) {
        System.out.println("------ 自定义角色校验规则：" + loginId + " 是否拥有角色 " + role);
        return Arrays.asList("ceo", "ceo:1", "ceo:2", "ceo:3", "ceo:4", "ceo:5").contains(role);
    }

}
