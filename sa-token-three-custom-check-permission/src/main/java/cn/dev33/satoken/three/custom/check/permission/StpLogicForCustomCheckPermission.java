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
package cn.dev33.satoken.three.custom.check.permission;

import cn.dev33.satoken.error.SaErrorCode;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义 StpLogic 的实现类，并重写部分权限校验、角色校验的方法，扩展功能
 *
 * @author RockMan
 * @since 1.35.0
 */
public class StpLogicForCustomCheckPermission extends StpLogic {

    @Autowired
    public StpCustomCheckRule stpCustomCheckRule;

    public StpLogicForCustomCheckPermission() {
        super(StpUtil.TYPE);
    }

    public StpLogicForCustomCheckPermission(String loginType) {
        super(loginType);
    }


    // --------------------- 重写权限校验部分方法 ---------------------

    /**
     * 判断：当前账号是否含有指定权限, 返回 true 或 false
     */
    @Override
    public boolean hasPermission(String permission) {
        return stpCustomCheckRule.hasPermission(getLoginId(), permission);
    }

    /**
     * 判断：指定账号 id 是否含有指定权限, 返回 true 或 false
     */
    @Override
    public boolean hasPermission(Object loginId, String permission) {
        return stpCustomCheckRule.hasPermission(loginId, permission);
    }

    /**
     * 校验：当前账号是否含有指定权限 [ 指定多个，必须全部验证通过 ]
     */
    @Override
    public void checkPermissionAnd(String... permissionArray) {
        // 先获取当前是哪个账号id
        Object loginId = getLoginId();

        // 如果没有指定权限，那么直接跳过
        if(permissionArray == null) {
            return;
        }

        // 开始校验
        for (String permission : permissionArray) {
            if(!hasPermission(loginId, permission)) {
                throw new NotPermissionException(permission, this.loginType).setCode(SaErrorCode.CODE_11051);
            }
        }
    }

    /**
     * 校验：当前账号是否含有指定权限 [ 指定多个，只要其一验证通过即可 ]
     */
    @Override
    public void checkPermissionOr(String... permissionArray) {
        // 先获取当前是哪个账号id
        Object loginId = getLoginId();

        // 如果没有指定要校验的权限，那么直接跳过
        if(permissionArray == null || permissionArray.length == 0) {
            return;
        }

        // 开始校验
        for (String permission : permissionArray) {
            if(hasPermission(loginId, permission)) {
                // 有的话提前退出
                return;
            }
        }

        // 代码至此，说明一个都没通过，需要抛出无权限异常
        throw new NotPermissionException(permissionArray[0], this.loginType).setCode(SaErrorCode.CODE_11051);
    }


    // --------------------- 重写角色校验部分方法 ---------------------

    /**
     * 判断：当前账号是否拥有指定角色, 返回 true 或 false
     */
    @Override
    public boolean hasRole(String role) {
        return stpCustomCheckRule.hasRole(getLoginId(), role);
    }

    /**
     * 判断：指定账号是否含有指定角色标识, 返回 true 或 false
     */
    @Override
    public boolean hasRole(Object loginId, String role) {
        return stpCustomCheckRule.hasRole(loginId, role);
    }

    /**
     * 校验：当前账号是否含有指定角色标识 [ 指定多个，必须全部验证通过 ]
     */
    @Override
    public void checkRoleAnd(String... roleArray) {
        // 先获取当前是哪个账号id
        Object loginId = getLoginId();

        // 如果没有指定要校验的角色，那么直接跳过
        if(roleArray == null) {
            return;
        }

        // 开始校验
        for (String role : roleArray) {
            if(!hasRole(loginId, role)) {
                throw new NotRoleException(role, this.loginType).setCode(SaErrorCode.CODE_11041);
            }
        }
    }

    /**
     * 校验：当前账号是否含有指定角色标识 [ 指定多个，只要其一验证通过即可 ]
     */
    @Override
    public void checkRoleOr(String... roleArray) {
        // 先获取当前是哪个账号id
        Object loginId = getLoginId();

        // 如果没有指定权限，那么直接跳过
        if(roleArray == null || roleArray.length == 0) {
            return;
        }

        // 开始校验
        for (String role : roleArray) {
            if(hasRole(loginId, role)) {
                // 有的话提前退出
                return;
            }
        }

        // 代码至此，说明一个都没通过，需要抛出无角色异常
        throw new NotRoleException(roleArray[0], this.loginType).setCode(SaErrorCode.CODE_11041);
    }

}
