## sa-token-three-token-prefix-compatible-cookie

### 插件介绍
众所周知 Sa-Token 在打开前缀模式后，是没法再继续使用 Cookie 模式鉴权的，本插件可以让 Sa-Token 在打开前缀模式时，Cookie 鉴权依然生效 


### 使用方式
在项目已经引入的 Sa-Token 依赖的情况下，继续引入此插件：

``` xml
<!-- Sa-Token 插件：自定义权限校验规则 -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-three-token-prefix-compatible-cookie</artifactId>
    <version>${sa-token.version}</version>
</dependency>
```

然后就可以让 Sa-Token 在打开前缀模式时，Cookie 鉴权依然生效了


### 联系方式
使用时如遇问题，请在 sa-token-three-plugin 中提交 issue 咨询