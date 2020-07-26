package test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import servlet.IndexServlet;

/**
 * @Author mubi
 * @Date 2020/7/25 20:45
 */
public class TestApplication {
	public static void main(String[] args) throws Exception{
		// 启动tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8099);
		tomcat.start();

		// web项目
		Context context = tomcat.addWebapp("/boot","/");
		// servlet & 映射配置
		IndexServlet indexServlet = new IndexServlet();
		tomcat.addServlet("/boot", "index", indexServlet);
		context.addServletMappingDecoded("/index.do", "index");

		tomcat.getServer().await();
	}
}
