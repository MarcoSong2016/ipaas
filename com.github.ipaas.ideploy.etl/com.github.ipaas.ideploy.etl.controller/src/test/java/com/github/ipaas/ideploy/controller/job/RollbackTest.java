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
package com.github.ipaas.ideploy.controller.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import com.github.ipaas.ideploy.controller.AgentDeloyStatus;
import com.github.ipaas.ideploy.controller.AgentHost;
import com.github.ipaas.ideploy.controller.CommonDataUtil;
import com.github.ipaas.ideploy.controller.DeployFlowStatus;
import com.github.ipaas.ideploy.controller.FileSysMonSwitch;
import com.github.ipaas.ideploy.controller.MulUnitExecutor;
import com.github.ipaas.ideploy.controller.RInfo;
import com.github.ipaas.ideploy.controller.Utils;
import com.github.ipaas.ideploy.controller.flow.FlowExecutor;
import com.github.ipaas.ideploy.controller.job.Rollback;
import com.github.ipaas.ideploy.controller.proc.ProcMeta;

/**
 * 回退失败流程测试
 * 
 * @author Chenql
 */
public class RollbackTest {

	@Mocked
	final CommonDataUtil util = null;

	@Mocked
	final Utils utils = null;
	@Mocked
	final FileSysMonSwitch fileSysMonSwitch = null;

	final MulUnitExecutor mulUnitExecutor = MulUnitExecutor.newInstance();

	final String deployFlowId = "junitTest";

	FlowExecutor flowExecutor = FlowExecutor.newInstance();

	final Rollback rollback = new Rollback();

	@Test
	public void executeTest() throws Exception {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("deployFlowId", deployFlowId);

		/********** 不允许回退 ***********/
		new Expectations() {
			{
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.ROLLBACK_SUCC);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 0;
			}
		};
		rollback.execute(params, null);

		// /********** 允许回退 ***********/

		final RInfo rInfo = new RInfo();
		rInfo.setServList(new ArrayList<AgentHost>());
		rInfo.setParams(new HashMap<String, Object>());

		final Map<String, Object> deployParams = new HashMap<String, Object>();
		final Integer sgId = 1;
		final Integer prevCodeId = 2;
		final Integer codeId = 3;
		final String servGroup = "servGroup";
		deployParams.put("usingCodeId", prevCodeId);
		deployParams.put("codeId", codeId);
		deployParams.put("sgId", sgId);
		deployParams.put("servGroup", servGroup);
		rInfo.getParams().putAll(deployParams);

		// 没有机器
		new Expectations() {
			{
				// , DeployFlowStatus.FINISH_WITH_ERR,
				// DeployFlowStatus.ROLLBACK_FAIL, DeployFlowStatus.WAIT_PREV,
				// DeployFlowStatus.FIRSTONE_EXCEP
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.INTER_BY_EXCEP);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;


				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
			//	FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());
				
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.FINISH_WITH_ERR);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;

				

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
				FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());
				
				
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.ROLLBACK_FAIL);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;
				
				
				

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
				FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());
				
				
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.FIRSTONE_EXCEP);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;
				
				

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
				FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());
				
				
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				returns(DeployFlowStatus.WAIT_PREV);

				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;
				
				

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
				FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());
				
				
			}
		};
		rollback.execute(params, null);
		rollback.execute(params, null);
		rollback.execute(params, null);
		rollback.execute(params, null);
		rollback.execute(params, null);

		AgentHost host2 = new AgentHost();
		host2.setProc(ProcMeta.DOWMLOAD_NEWCODE);
		host2.setStatus(AgentDeloyStatus.UNDERWAY);
		host2.setIp("host2");
		rInfo.getServList().add(host2);
		AgentHost host1 = new AgentHost();
		host1.setIp("host1");
		host1.setProc(ProcMeta.WAITFOR_PREV_FISRT);
		host1.setStatus(AgentDeloyStatus.EXCEPT);
		rInfo.getServList().add(host1);

		AgentHost host3 = new AgentHost();
		host3.setProc(ProcMeta.STOP_SERVER);
		host3.setIp("host3");
		host3.setStatus(AgentDeloyStatus.EXCEPT);
		rInfo.getServList().add(host3);

		new Expectations(MulUnitExecutor.class) {
			{
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				result = DeployFlowStatus.FINISH_WITH_ERR;
				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;
				// 重置状态为正在回退
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACKING);
				times = 1;

				// 每次更新的服务器数
				Utils.getPerUpServNo(2);
				times = 1;
				result = 1;

				// 执行更新
				mulUnitExecutor.execute();
				times = 2;
				// 全部更新成功
				mulUnitExecutor.getFailProcList();
				times = 1;
				result = new ArrayList<String>();

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				times = 1;
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_SUCC);
				times = 1;
				CommonDataUtil.updateCodeStatus4RollBackSucc(sgId, codeId);
				times = 1;
				// 启动文件监控模块
				//FileSysMonSwitch.startFileSysMon(deployFlowId, servGroup, rInfo.getServList());

			}
		};

		rollback.execute(params, null);
		//
		// // 回退失败

		new Expectations() {
			{
				CommonDataUtil.getDeployFlowStatus(deployFlowId);
				times = 1;
				result = DeployFlowStatus.FINISH_WITH_ERR;
				CommonDataUtil.getRlInfo(deployFlowId);
				times = 1;
				result = rInfo;
				// 重置状态为正在回退
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACKING);
				times = 1;

				// 每次更新的服务器数
				Utils.getPerUpServNo(2);
				times = 1;
				result = 1;

				// 执行更新
				mulUnitExecutor.execute();
				times = 2;
				// 回退失败
				mulUnitExecutor.getFailProcList();
				times = 1;
				List<String> failureList = new ArrayList<String>();
				failureList.add("host1");
				result = failureList;

				CommonDataUtil.updateDepployEndTime(deployFlowId, (Date) any);
				CommonDataUtil.updateDeplyFlowStatus(deployFlowId, DeployFlowStatus.ROLLBACK_FAIL);

			}
		};

		rollback.execute(params, null);

	}
}
