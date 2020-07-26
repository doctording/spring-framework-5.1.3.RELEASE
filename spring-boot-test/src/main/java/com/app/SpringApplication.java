package com.app;

import org.apache.catalina.startup.Tomcat;

/**
 * @Author mubi
 * @Date 2020/7/26 11:54
 */
public class SpringApplication {
	public static void run(){
		// 启动tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8098);
		// 添加web上下文
		tomcat.addWebapp("/boot", "/");
		try {
			tomcat.start();
		}catch (Exception e){

		}
		tomcat.getServer().await();
	}
}
