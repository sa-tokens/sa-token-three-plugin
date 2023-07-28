## sa-token-three-custom-check-permission

### 插件介绍
自定义 Sa-Token 的鉴权逻辑，不再一次性返回整个权限码集合给框架判断，而是根据自定义验证规则返回 true 或 false 给框架

### 使用方式
在项目已经引入的 Sa-Token 依赖的情况下，继续引入此插件：

``` xml
<!-- Sa-Token 插件：自定义权限校验规则 -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-three-custom-check-permission</artifactId>
    <version>${sa-token.version}</version>
</dependency>
```

在代码中添加类，实现 `StpCustomCheckRule` 接口：

``` java
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
```

然后就可以像往常一样调用 Sa-Token 的鉴权 API 了：

``` java
// 测试权限校验
@RequestMapping("hasPermission")
public SaResult hasPermission() {
	System.out.println(StpUtil.hasPermission("user:add"));
	System.out.println(StpUtil.hasPermission(10044, "user:add"));

	StpUtil.checkPermission("user:1");
	StpUtil.checkPermissionOr("user:2", "user:3");
	StpUtil.checkPermissionAnd("user:4", "user:5");

	return SaResult.ok();
}

// 测试角色校验
@RequestMapping("hasRole")
public SaResult hasRole() {
	System.out.println(StpUtil.hasRole("ceo"));
	System.out.println(StpUtil.hasRole(10044, "ceo"));

	StpUtil.checkRole("ceo:1");
	StpUtil.checkRoleOr("ceo:2", "ceo:3");
	StpUtil.checkRoleAnd("ceo:4", "ceo:5");

	return SaResult.ok();
}
```


### 联系方式
使用时如遇问题，请在 sa-token-three-plugin 中提交 issue 咨询