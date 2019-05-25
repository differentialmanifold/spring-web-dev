package com.example.platform.module.common.enumtype;

import org.apache.commons.lang3.StringUtils;

/**
 */
public enum EnvEnum {
	Local("local", "本地开发"),
	Dev("dev", "开发环境"),
	Test("test", "测试环境"),
	Pro("pro", "生产环境")
	;
	
	private String type;
	
	private String desc;
	
	EnvEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static String getDescByType(String type){
		String desc = "";
		if(StringUtils.equalsIgnoreCase(type, EnvEnum.Local.getType())){
			desc = EnvEnum.Local.getDesc();
		}else if(StringUtils.equalsIgnoreCase(type, EnvEnum.Dev.getType())) {
			desc = EnvEnum.Dev.getDesc();
		}else if(StringUtils.equalsIgnoreCase(type, EnvEnum.Test.getType())){
			desc = EnvEnum.Test.getDesc();
		}else if(StringUtils.equalsIgnoreCase(type, EnvEnum.Pro.getType())){
			desc = EnvEnum.Pro.getDesc();
		}else {
			desc = type;
		}
		return desc;
	}
	
	
	
}

