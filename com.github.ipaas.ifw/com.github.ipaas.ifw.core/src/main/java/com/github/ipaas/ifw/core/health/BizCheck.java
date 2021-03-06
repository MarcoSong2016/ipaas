/*
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

package com.github.ipaas.ifw.core.health;

/**
 * 用于Heavy检查
 * @author Chenql
 *
 */
public interface BizCheck {
    
    /**
     * 
     * 重量级业务检测方法
     * 
     * @return 返回字符串表示的可用性状态。1表示可用，其他表示不可用的信息.
     */
    public String checkBiz();

}
