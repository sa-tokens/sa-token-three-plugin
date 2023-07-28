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

/**
 * 自定义权限验证规则接口，通过实现此接口并注入容器，来自定义权限验证规则
 *
 * @author RockMan
 * @since 1.35.0
 */
public interface StpCustomCheckRule {

    /**
     * 指定 loginId 是否持有指定权限码，返回 true 或 false
     * @param loginId 账号id
     * @param permission 权限码
     * @return /
     */
    boolean hasPermission(Object loginId, String permission);

    /**
     * 指定 loginId 是否持有指定角色标识，返回 true 或 false
     * @param loginId 账号id
     * @param role 角色标识
     * @return /
     */
    boolean hasRole(Object loginId, String role);

}
