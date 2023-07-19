package cn.dev33.satoken.three.example;

import cn.dev33.satoken.temp.SaTempInterface;
import cn.dev33.satoken.util.SaTokenConsts;

/**
 * 示例：自定义 SaTempInterface 的实现类，并重写部分方法，扩展功能
 *
 * @author click33
 * @since 1.35.0
 */
public class SaTempForExample implements SaTempInterface {

    /**
     * 初始化 SaTempForExample
     *
     */
    public SaTempForExample() {
        System.out.println("------------- 自定义的 SaTempForExample 组件被加载 -------------");
    }

    /**
     * 重写 createToken 方法，加上自定义逻辑，这样当开发者调用 SaTempUtil.createToken(1001, 1000) 时，就会执行我们自定义的逻辑
     * @return /
     */
    @Override
    public String createToken(Object value, long timeout) {
        System.out.println("------------- 重写了 createToken() 方法，执行自定义逻辑 -------------");
        return createToken(SaTokenConsts.DEFAULT_TEMP_TOKEN_SERVICE, value, timeout);
    }

}
