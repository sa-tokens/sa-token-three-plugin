/*
 * Copyright 2020-2099 sa-token.cc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dev33.satoken.three.stp;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.dev33.satoken.util.SaTokenConsts;

/**
 * 自定义 StpLogic 的实现类，并重写登录校验相关方法，使 token-prefix 兼容 Cookie 模式鉴权
 *
 * @author yigetao
 * @since 1.35.0
 */
public class StpLogicForTokenPrefixCompatibleCookie extends StpLogic {

    public StpLogicForTokenPrefixCompatibleCookie() {
        super(StpUtil.TYPE);
    }

    public StpLogicForTokenPrefixCompatibleCookie(String loginType) {
        super(loginType);
    }


    /**
     * 重写实现：使 token-prefix 兼容 Cookie 模式
     */
    @Override
    public String getTokenValueNotCut(){

        // 获取相应对象
        SaStorage storage = SaHolder.getStorage();
        SaRequest request = SaHolder.getRequest();
        SaTokenConfig config = getConfigOrGlobal();
        String keyTokenName = getTokenName();
        String tokenValue = null;

        // 1. 先尝试从 Storage 存储器里读取
        if(storage.get(splicingKeyJustCreatedSave()) != null) {
            tokenValue = String.valueOf(storage.get(splicingKeyJustCreatedSave()));
        }
        // 2. 再尝试从 请求体 里面读取
        if(tokenValue == null && config.getIsReadBody()){
            tokenValue = request.getParam(keyTokenName);
        }
        // 3. 再尝试从 header 头里读取
        if(tokenValue == null && config.getIsReadHeader()){
            tokenValue = request.getHeader(keyTokenName);
        }
        // 4. 最后尝试从 cookie 里读取
        if(tokenValue == null && config.getIsReadCookie()){
            tokenValue = request.getCookieValue(keyTokenName);
            String configTokenPrefix = config.getTokenPrefix();
            if(SaFoxUtil.isNotEmpty(configTokenPrefix)) {
                tokenValue = configTokenPrefix + SaTokenConsts.TOKEN_CONNECTOR_CHAT + tokenValue;
            }
        }

        // 5. 至此，不管有没有读取到，都不再尝试了，直接返回
        return tokenValue;
    }


}
