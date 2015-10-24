/**
* Licensed under the Apache License, Version 2.0 (the "License");  *
 you may not use this file except in compliance with the License.  *
 You may obtain a copy of the License at  *
  *
      http://www.apache.org/licenses/LICENSE-2.0  *
  *
 Unless required by applicable law or agreed to in writing, software  *
 distributed under the License is distributed on an "AS IS" BASIS,  *
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 See the License for the specific language governing permissions and  *
 limitations under the License. */

package com.github.ipaas.ideploy.controller.proc;
 

import java.util.Date;

import com.github.ipaas.ideploy.controller.AgentDeloyEvt;
import com.github.ipaas.ideploy.controller.CommonDataUtil;
import com.github.ipaas.ideploy.controller.DeployProcStatus;


/** 
 * 首台更新等待用户指令 
 * @author  wudg
 */

public class AwaitUserCmd4FirstOneProc   extends Proc {
	
	@Override
	public void execute() throws Exception {
		  
		//事件日志
		AgentDeloyEvt evt=new AgentDeloyEvt();
		evt.setCreateTime(new Date());
		evt.setDeployFlowId(procInput.getFlowId());
		evt.setEvtDescr("正在"+getProcMeta().getDescr());
		evt.setAgentIp(procInput.getExecTerminal());
		evt.setProc(getProcMeta().getKey());
		evt.setStatus(DeployProcStatus.UNDERWAY);
		CommonDataUtil.createAgentEvtLog(evt);
	}

}
