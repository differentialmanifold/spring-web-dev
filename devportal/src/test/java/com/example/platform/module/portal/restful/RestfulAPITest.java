package com.example.platform.module.portal.restful;


import com.alibaba.fastjson.JSONObject;
import com.example.platform.module.common.http.HttpProcess;
import com.example.platform.module.common.http.RestfulHttpFactory;

import com.example.platform.module.common.http.HttpClient.DevHttpResponse;
import com.example.platform.module.common.quartz.entity.DevQuartzJobVO;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.common.utils.UserDetailUtil;
import com.example.platform.module.dao.vo.GroupVO;

public class RestfulAPITest {

	private static final String query_path = "http://localhost:8080/dev-portal/api";
	private static final String query_groups_path = query_path + "/groups";
	private static final String query_scheduler_path = query_path + "/scheduler";
	private static final HttpProcess http = RestfulHttpFactory.createDefaultHttpProcess();
	private static final int retryTimes = 3;
	private static final int retrySleepSeconds = 1;

	private static Integer id = 0;
	
	
	
	public static void main(String[] args){
		RestfulAPITest rest = new RestfulAPITest();
		try{
		rest.addGroup();
		rest.updateGroup();
		rest.deleteGroup();
		rest.runJob();
		rest.addQuartzJob();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private  void addGroup()throws Exception{
		GroupVO req = new GroupVO();
		req.setGroupName("restfulapi-test");
		req.setCreateUser(UserDetailUtil.getUserName());
		DevHttpResponse resp = http.post(query_groups_path , JSONObject.toJSONString(req) , retryTimes, retrySleepSeconds);
		ResponseResult result = JSONObject.parseObject(resp.getData(), ResponseResult.class);
		System.out.println("add resp: "+resp);
		id = ((JSONObject)result.getData()).getInteger("id");
		System.out.println("add id is " + id);
	}
	
	private  void updateGroup()throws Exception{
		GroupVO req = new GroupVO();
		req.setId(id);
		req.setGroupName("restfulapi-test");
		req.setCreateUser(UserDetailUtil.getUserName());
		req.setCron("0 0 0 * * ?");
		DevHttpResponse resp = http.put(query_groups_path , JSONObject.toJSONString(req) , retryTimes, retrySleepSeconds);
		System.out.println("update resp: "+resp);
	}

	
	private  void deleteGroup()throws Exception{
		DevHttpResponse resp = http.delete(query_groups_path + "/" +  id , retryTimes, retrySleepSeconds);
		System.out.println("delete resp: "+resp);
	}

	private void runJob() throws Exception {
		DevHttpResponse resp = http.get(query_groups_path + "/runjob", retryTimes, retrySleepSeconds);
		System.out.println("run job resp: "+resp);
	}

	private void addQuartzJob() throws Exception {
		DevQuartzJobVO req = new DevQuartzJobVO();
		req.setJobName("dev-job-name");
		req.setJobGroup("dev-job-group");
		req.setCronExpression("0/2 * * * * ?");
		DevHttpResponse resp = http.post(query_scheduler_path, JSONObject.toJSONString(req), retryTimes, retrySleepSeconds);
		System.out.println("add quartz job resp: "+resp);
	}

}
