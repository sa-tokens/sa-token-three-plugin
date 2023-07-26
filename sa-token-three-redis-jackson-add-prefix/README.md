## sa-token-three-redis-jackson-add-prefix

### 插件介绍
为 Sa-Token 在 Redis 中的 key 添加上指定前缀，解决在多个项目使用同一个 Redis 同一 db 索引时，key 冲突的问题。

### 使用方式
在项目已经引入的 Sa-Token 依赖的情况下，继续引入此插件：

``` xml
<!-- Sa-Token 插件：为 Sa-Token 在 Redis 中的 key 添加上指定前缀 -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-three-redis-jackson-add-prefix</artifactId>
    <version>${sa-token.version}</version>
</dependency>
```

然后在 application.yml 中配置：
``` yaml
# sa-token 配置
sa-token: 
    # redis 存储数据时 key 的前缀
    redisKeyPrefix: "abc:"
```

然后正常调用 `StpUtil.login(xxx)` 即可在 Redis 存储数据时自动带上 `abc:` 前缀

##### 注意点：
1. 此插件不可以和 `sa-token-redis-jackson` 同时引入。
2. 配置文件中的 `redisKeyPrefix` 必须是小驼峰形式，不能是中划线形式，错误示例：`redis-key-prefix`。
3. 相比原生的 `sa-token-redis-jackson` 插件，本插件会稍微性能下降（多了拼接key前缀的步骤）。


### 联系方式
使用时如遇问题，请在 sa-token-three-plugin 中提交 issue 咨询