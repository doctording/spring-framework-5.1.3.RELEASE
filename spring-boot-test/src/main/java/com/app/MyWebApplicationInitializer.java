package com.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	/**
	 * tomcat 启动后会加载 javax.servlet.ServletContainerInitializer
	 * 		进入 org.springframework.web.SpringServletContainerInitializer#onStartup
	 * 	而ServletContainerInitializer加上了@HandlesTypes(WebApplicationInitializer.class)	会加载所有的WebApplicationInitializer
	 * 然后调用 WebApplicationInitializer 的 onStartup 方法
 	 */
	// ServletContext 即 web 容器的上下文
	@Override
	public void onStartup(ServletContext servletCxt) {

		// Load Spring web application configuration
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		// Spring 程序（容器）的上下文对象
		// 					eg: ClassPathXmlApplicationContext
		ac.register(AppConfig.class);
		// Spring的初始化
//		ac.refresh();
		ac.setServletContext(servletCxt);
		ac.refresh();

		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(ac);
		ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("*.do");
	}
}
