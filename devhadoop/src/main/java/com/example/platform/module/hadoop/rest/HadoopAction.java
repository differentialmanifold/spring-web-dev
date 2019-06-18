package com.example.platform.module.hadoop.rest;


import com.example.platform.module.common.action.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/hadoop")
public class HadoopAction extends BaseAction {

    private static final Logger logger = LoggerFactory.getLogger(HadoopAction.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {

        logger.info("hadoop process is running");

        return "It's OK , hadoop process is running !";
    }

    @RequestMapping(value = "/hivetest", method = { RequestMethod.GET })
    @ResponseBody
    public String hivetest() {

        logger.info("start run hive test");

        logger.info("finished run hive test");

        return "hive test finished!";
    }
}
