# 快速上手，如何为 Sa-Token 开发一个插件，sa-token-three-plugin 提交 pr 步骤

为 sa-token-three-plugin 提交插件可以简单的分为 5 个步骤：

1. fork 仓库到自己的主页。
2. 将 fork 的仓库克隆到本地。
3. 写代码，添加想要开发的插件（插件代码、插件介绍、插件使用demo）。
4. 提交、推送代码到fork库。
5. 在 fork 库提交 pr 到 sa-token-three-plugin。

下面依次详细介绍每一个步骤：

### 一、fork 仓库到自己的主页

![](https://oss.dev33.cn/sa-token/three-plugin/1-fork.png)


### 二、将 fork 的仓库克隆到本地

这里需要注意，因为 sa-token-three-plugin 不是一个独立的仓库，它是依附于 Sa-Token 主仓库的，
所以我们需要先把 Sa-Token 主仓库拉取到本地。

``` bash
git clone https://gitee.com/dromara/sa-token.git
```

这里推荐大家使用小乌龟可视化工具（TortoiseGit），比直接手敲命令行方便多了：

![](https://oss.dev33.cn/sa-token/three-plugin/2-clone-sa-token.png)

克隆成功后，大概长这样：

![](https://oss.dev33.cn/sa-token/three-plugin/2-clone-sa-token-ok.png)

接下来**先双击进入 sa-token 文件夹，然后再拉取 sa-token-three-plugin 仓库**

注意此处拉取的应该是你自己主页的fork库，而不是 sa-token-three-plugin 原始库：

``` bash
git clone https://gitee.com/xxx/sa-token-three-plugin.git
```

中间的 xxx 应该是你自己的用户名，具体这个地址从这里找到：

![](https://oss.dev33.cn/sa-token/three-plugin/2-git-url.png)


拉取成功后，大约长这个样子：

![](https://oss.dev33.cn/sa-token/three-plugin/2-clone-sa-token-three-plugin-ok.png)

注意最终结果应该是 sa-token-three-plugin 作为 sa-token 的一个子文件夹存在的。


### 三、写代码，添加想要开发的插件

比如说我现在有个想法：

> sa-token 默认生成的 token 是uuid格式，太单调了，我想给它加一个统一的前缀 sa-token，
> 使之最终效果呈现为：`sa-token--abfd7213-a9a9-b611-283f-d04167d51e9a`

如果要以插件的形式实现这个功能，大体需要以下几步：

1. 创建一个子模块，比如 `sa-token-three-add-prefix`（请按照 sa-token-three-xxx 格式命名插件）。
2. 写代码，完成插件功能。
3. 在插件目录创建一个 README.md ，简单介绍一下插件使用步骤。
4. 在 sa-token-three-demo 目录里创建一个 demo，例如`sa-token-three-add-prefix-demo`，简单演示一下插件使用方法。


#### 3.1、创建一个子模块

注意我们先不要从 idea 导入项目，我们先把插件模块创建好。

你不用从零开始新建 maven 子模块，只需要复制仓库里提供的示例模块然后重命名一下即可：

![](https://oss.dev33.cn/sa-token/three-plugin/3-copy-case.png)

最终的样子：

![](https://oss.dev33.cn/sa-token/three-plugin/3-copy-case-ok.png)

除了要重命名文件夹的名称，我们还需要打开 `sa-token-three-add-prefix` 目录里的 `pom.xml` 文件，
重命名 `<artifactId>` 节点里的项目名称，如下面所示：
``` xml
<!-- 这个值原先是 sa-token-three-example-plugin，现在把它改为 sa-token-three-add-prefix -->
<artifactId>sa-token-three-add-prefix</artifactId>
```

然后打开 `/sa-token-three-plugin` 目录下的 pom.xml 文件，在 `<modules>` 节点添加上我们的新模块，如下面所示：

``` xml
<!-- ... 其他节点 ... -->

<!-- 所有子模块 -->
<modules>
	<module>sa-token-three-example-plugin</module>
	<module>sa-token-three-add-prefix</module>
</modules>

<!-- ... 其他节点 ... -->
```

一切准备完毕，可以导入项目了。


#### 3.2、导入项目

从 idea 里导入 Sa-Token 主仓库项目，效果如图：

![](https://oss.dev33.cn/sa-token/three-plugin/3-import-three-plugin.png)

注意初次导入 Sa-Token 项目后，里面的 sa-token-three-plugin 项目还是处于未导入状态的，
请按照上述红字步骤再导入 sa-token-three-plugin 项目。

现在开始写代码。


#### 3.3、开始写插件代码

打开我们的 `sa-token-three-add-prefix` 模块，里面有两个示例类 `SaExampleUtil`、`SaTempForExample` 我们用不到，可以先删除掉。

现在开始分析功能点，由于创建 token 的步骤主要由 `StpLogic` 类的 `createTokenValue` 方法完成，
所有我们要给 token 添加前缀的话，就需要自定义一个 `StpLogic` 的子类，然后重写 `createTokenValue` 方法来完成。

新建包 `cn.dev33.satoken.three.addprefix`，然后新建类 `StpLogicForAddPrefix.java`，复制以下代码：

``` java
/**
 * 示例：自定义 StpLogic 的实现类，并重写部分方法，扩展功能
 *
 * @author xxx
 * @since 1.35.0
 */
public class StpLogicForAddPrefix extends StpLogic {

    public StpLogicForAddPrefix() {
        super(StpUtil.TYPE);
        System.out.println("------------- 自定义的 StpLogicForAddPrefix 组件被加载 -------------");
    }

    public StpLogicForAddPrefix(String loginType) {
        super(loginType);
    }

    /**
     * 重写 createToken 方法，这样当框架创建 token 时，就会执行我们自定义的逻辑
     */
    @Override
    public String createTokenValue(Object loginId, String device, long timeout, Map<String, Object> extraData) {
        // 拼接上指定前缀
        return "sa-token--" + SaStrategy.instance.createToken.apply(loginId, loginType);
    }

}
```

现在组件的代码已经完成，但是默认情况下，组件不会自动注入到框架中，我们还需要做一些工作，
让 SpringBoot 的 SPI 机制帮我们加载组件。

打开组件的 `\resources\META-INF\spring.factories` 文件，把组件的完全限定名填进去，例如：
``` txt
org.springframework.boot.autoconfigure.EnableAutoConfiguration=cn.dev33.satoken.three.addprefix.StpLogicForAddPrefix
```

以上是 SpringBoot2.x 的SPI注入方式，为了让组件在 SpringBoot3.x 环境下也能正常SPI注入，我们还需要打开
`\resources\META-INF\spring\org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件，写上：

``` txt
cn.dev33.satoken.three.addprefix.StpLogicForAddPrefix
```

ok，至此，组件代码已经书写完毕。


#### 3.4、给插件写个使用介绍

为了让此插件被更多人学会使用，你需要在插件根目录创建一个 `README.md` 文件，使用 markdown 格式介绍一下插件的使用方式

例如，打开 `\sa-token-three-add-prefix\` 插件根目录，创建 `README.md` 文件：


`````` markdown
## sa-token-three-add-prefix 插件

### 插件介绍
为 Sa-Token 生成的 token 添加上指定前缀 

### 使用方式
在项目已经引入的 Sa-Token 依赖的情况下，继续引入此插件

``` xml
<!-- Sa-Token 插件：为 token 添加指定依赖 -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-three-add-prefix</artifactId>
    <version>${sa-token.version}</version>
</dependency>
```

然后正常调用 `StpUtil.login(xxx)` 即可生成带有指定前缀的 token 

### 联系方式
使用时如遇问题，可添加 xxx 联系解决
``````


#### 3.5、给插件写个 demo 示例
文档介绍可能无法描述清楚所有的使用细节，你可以再创建一个 demo 示例，帮助大家学习怎么使用插件

打开 `\sa-token-three-plugin\sa-token-three-demo\` 文件夹，复制一份 `\sa-token-three-example-plugin-demo\`：

![](https://oss.dev33.cn/sa-token/three-plugin/3-copy-three-plugin-demo.png)

然后重命名，像这样：

![](https://oss.dev33.cn/sa-token/three-plugin/3-copy-three-plugin-demo-ok.png)

双击进入 `\sa-token-three-add-prefix-demo\`，打开 pom.xml 文件，重命名 `<artifactId>` 节点里的项目名称，如下面所示：
``` xml
<!-- 这个值原先是 sa-token-three-example-plugin-demo，现在把它改为 sa-token-three-add-prefix-demo -->
<artifactId>sa-token-three-add-prefix-demo</artifactId>
```

现在打开 idea ，导入这个项目：

![](https://oss.dev33.cn/sa-token/three-plugin/3-import-three-plugin-demo.png)

打开这个项目的 pom.xml，引入你刚刚开发的插件依赖，像这样：

``` xml
<!-- 引入 sa-token-three-add-prefix 插件 -->
<dependency>
	<groupId>cn.dev33</groupId>
	<artifactId>sa-token-three-add-prefix</artifactId>
	<version>${sa-token.version}</version>
</dependency>
```

接下来，你就可以自由发挥了，写任意的代码，只要足够简单、完整的演示这个插件如何使用即可。


#### 3.6、给插件写上链接
当你写完上述代码，就可以给你的插件加个链接了。

打开 sa-token-three-plugin 项目的根目录 README.md 文件，在 **插件列表** 处，挂上插件链接，如下图所示：

![](https://oss.dev33.cn/sa-token/three-plugin/3-add-link.png)

这一步也可以不写，如果pr成功合并，我们会帮你补上这个链接。


### 四、提交、推送代码到fork库

大体步骤为：

1. 先在 `sa-token-three-plugin`项目里 commit 你刚刚创建的所有新文件。
2. 然后将本次提交 push 到你的 fork 库中。

这是 git 提交、推送代码的最基本步骤，这里就不过多赘述了，实在不会的同学可自行百度，能搜到很多相关文章。


### 五、在 fork 库提交 pr 到 sa-token-three-plugin

如果你的代码已经提交、推送成功，那么现在你可以将插件 pr 到 sa-token-three-plugin 原仓库了！

1、打开你的 fork 库首页，点击 `pull requests` -> `新建 pull request`

![](https://oss.dev33.cn/sa-token/three-plugin/4-pull-request.png)

2、然后写好本次 pr 的标题、介绍

![](https://oss.dev33.cn/sa-token/three-plugin/4-edit-pr-info.png)

3、点击下方的 `创建 Pull Request` 按钮提交即可

![](https://oss.dev33.cn/sa-token/three-plugin/4-edit-pr-info-ok.png)

大功告成，接下来等待 pr 合并即可！




