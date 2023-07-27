# sa-token-three-plugin

#### 仓库介绍
Sa-Token 三方插件合集，希望本仓库可以最大程度汇集社区的力量，插件将不定时与 Sa-Token 主仓库一起发布到 Maven 中心仓库。

+ [为什么创建这个仓库？sa-token-three-plugin 仓库介绍](README_REASON.md)

+ [快速上手，如何为 Sa-Token 开发一个插件，sa-token-three-plugin 提交 pr 步骤](README_PR_STEP.md)


#### 插件列表
目前已开发的插件列表：

| 插件								| 作者				| 介绍											| 是否已发布	| 使用文档	|
| :--------							| :--------			| :--------										| :--------	| :--------	|
| sa-token-three-example-plugin		| 孔明				| 为第三方插件开发时提供的示例工程					| 未发布		| [详情](sa-token-three-example-plugin/README.md)	|
| sa-token-three-redis-jackson-add-prefix	| RockMan	| 为 Sa-Token 在 Redis 中的 key 添加上指定前缀	| 未发布		| [详情](sa-token-three-redis-jackson-add-prefix/README.md)	|


#### 使用方式
如果是已发布的插件，直接在 pom.xml 中引入依赖即可，形如：
``` xml
<!-- Sa-Token 插件：xxx -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-three-xxx插件名称</artifactId>
    <version>${sa-token.version}</version>
</dependency>
```

如果插件尚未发布，有三种方式使用：

- 方式1：等待插件发布。
- 方式2：直接将插件代码文件复制到项目，有些插件只有一两个类，直接复制也不麻烦。
- 方式3：如果你的项目是 maven 多模块项目，你可以将插件作为一个子模块引入到你的项目里。








