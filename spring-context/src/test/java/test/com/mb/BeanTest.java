package test.com.mb;

import com.mb.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/6/10 22:22
 */
public class BeanTest {

	@Test
	public void testClassPathXmlApplicationContextBean() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring.xml");
		User user = (User) applicationContext.getBean("user");
		Assert.assertTrue(user != null);
		Assert.assertTrue(user.getTestStr().equals("testStr"));
	}

}
