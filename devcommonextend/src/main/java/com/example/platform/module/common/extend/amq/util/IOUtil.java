package com.example.platform.module.common.extend.amq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

public class IOUtil {
	
	private static Logger logger = LoggerFactory.getLogger(IOUtil.class);
	
	public static void closeQuietly(Closeable in){
		if(in != null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("i/o close error ==>" , e);
			}
		}
	}
	
	/**
	 * 获得输入流
	 * 
	 * @param file 文件
	 * @return 输入流
	 * @throws FileNotFoundException
	 */
	public static BufferedInputStream getInputStream(File file) throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(file));
	}
	
	/**
	 * 获得一个文件读取器
	 * @param in 输入流
	 * @param charsetName 字符集名称
	 * @return BufferedReader对象
	 * @throws IOException
	 */
	public static BufferedReader getReader(InputStream in, String charsetName) throws IOException{
		return getReader(in, Charset.forName(charsetName));
	}
	
	/**
	 * 获得一个文件读取器
	 * @param in 输入流
	 * @param charset 字符集
	 * @return BufferedReader对象
	 * @throws IOException
	 */
	public static BufferedReader getReader(InputStream in, Charset charset) throws IOException{
		if(null == in){
			return null;
		}
		
		InputStreamReader reader = null;
		if(null == charset) {
			reader = new InputStreamReader(in);
		}else {
			reader = new InputStreamReader(in, charset);
		}
		
		return new BufferedReader(reader);
	}
	
}
