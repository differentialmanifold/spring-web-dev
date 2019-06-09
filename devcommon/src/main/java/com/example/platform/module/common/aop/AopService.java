package com.example.platform.module.common.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("aopService")
public class AopService {

    private final Logger LOG = LoggerFactory.getLogger(AopService.class);

    @RequestLog(name = "user1", description = "this is user1")
    public void getAllUser(String input) throws Exception {
        LOG.info("input parameter is {}", input);

    }
}
