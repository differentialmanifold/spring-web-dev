package com.example.platform.module.portal.mybatis;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.dao.dto.GroupDTO;
import com.example.platform.module.portal.rest.GroupAction;
import com.example.platform.module.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml"})
public class MapperTest {
	
	@Autowired
	private GroupService groupService;

	@Autowired
	private GroupAction groupAction;
	
	@Test
	public void test() throws Exception {

		Page<GroupDTO> page = new Page<GroupDTO>(1, 10);

		Page<GroupDTO> jobPage = groupService.getListWithTLName(page, null, null,
				0, 0, -1);
		
		System.out.println(jobPage);
		System.out.println("=========");
	}

}
