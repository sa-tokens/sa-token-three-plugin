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
package cn.dev33.satoken.dao;

import cn.dev33.satoken.session.SaSession;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Jackson 定制版 SaSession，忽略 timeout 等属性的序列化
 *  
 * @author RockMan
 * @since 1.35.0
 */
@JsonIgnoreProperties({"timeout"})
public class SaSessionForJacksonCustomized extends SaSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7600983549653130681L;

	public SaSessionForJacksonCustomized() {
		super();
	}

	/**
	 * 构建一个Session对象
	 * @param id Session的id
	 */
	public SaSessionForJacksonCustomized(String id) {
		super(id);
	}

}
