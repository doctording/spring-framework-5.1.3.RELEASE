package test.com.mb.fatory;

import com.mb.factory.Go;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 13:21
 */
public class FactoryBeanTest {

	@Test
	public void testDependencySpring() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-factory-bean.xml");
		Go go = (Go) applicationContext.getBean("go");
		go.out();
	}

}
