package com.example.platform.module.common.extend.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * App properties加载工具 默认加载classpath中的application.properties
 *
 */
public class PropertiesUtil {

	private static Properties config = null;

	static {
		init();
	}

	/**
	 * Constructor.
	 */
	private PropertiesUtil() {
	}

	/**
	 * 初使化
	 * <p>
	 * 描述:
	 * </p>
	 */
	private static void init() {
		if (config == null) {
			config = new Properties();
		} else {
			config.clear();
			config = null;
			config = new Properties();
		}
		String configFile = PropertiesUtil.class.getResource("/app.properties").getPath();
		initConfig(config, configFile);

	}

	public static void refreshConfig() {
		init();
	}

	/**
	 * initialization.
	 */
	private static void initConfig(Properties config, String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			config.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			// LOG.error("PropertiesUtil::init->can't load config.properites",
			// e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// LOG.error( "PropertiesUtil::init->Input stream close
					// exception:" ,e);
				}
			}
		}
	}

	/**
	 * Get property value by key.
	 * 
	 * @param key
	 *            property
	 * @return String
	 */
	public static String getConfigProperty(String key) {
		return config.getProperty(key);
	}

	public static String getConfigProperty(String key, String defaultValue) {
		String value = config.getProperty(key);
		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	/**
	 * Get property value by key.
	 * 
	 * @param key
	 *            property
	 * @return boolean
	 */
	public static Boolean getBooleanConfigProperty(String key) {
		String value = config.getProperty(key);
		if (value == null) {
			return false;
		}
		return Boolean.parseBoolean(value);
	}

}
