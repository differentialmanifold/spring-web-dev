package com.example.platform.module.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppProperty {
	
	@Value("#{appProp['env']}")
	private String  env;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	
}
