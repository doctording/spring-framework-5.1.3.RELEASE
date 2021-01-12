package test.com.mb;

import com.mb.lifexml.FullBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 15:43
 */
public class BeanLifeXmlTest {

	@Test
	public void testClassPathXmlApplicationContextBean() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-full-life.xml");
		System.out.println("----------------applicationContext");
		FullBean fullBean = (FullBean)applicationContext.getBean("fullBean");
		System.out.println("fullBean.getName:" + fullBean.getName());
		((ClassPathXmlApplicationContext) applicationContext).close();
	}

}
