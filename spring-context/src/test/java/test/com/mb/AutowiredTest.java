package test.com.mb;

import com.mb.autowired.Ca;
import com.mb.autowired.IndexConfig;
import com.mb.autowired.IndexService;
import com.mb.autowired.Ta;
import com.mb.autowired.TaConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 13:21
 */
public class AutowiredTest {

	@Test
	public void testAutoByType() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-autowired-by-type.xml");
		Ca ca = (Ca) applicationContext.getBean("ca");
		Assert.assertTrue(ca != null);
	}

	@Test
	public void testAutoByName() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-autowired-by-name.xml");
		Ca ca = (Ca) applicationContext.getBean("ca");
		Assert.assertTrue(ca != null);
	}

	@Test
	public void testAutoByNo() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-autowired-no.xml");
		Ca ca = (Ca) applicationContext.getBean("ca");
		Assert.assertTrue(ca != null);
		ca.cbOut();
	}

	@Test
	public void testAnnotationAuto() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(TaConfig.class);
		Ta ta = (Ta) applicationContext.getBean("ta");
		System.out.println("ta i:" + ta.getI());
	}

	@Test
	public void testAutoConstructor() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(IndexConfig.class);
		IndexService indexService = (IndexService) applicationContext.getBean("indexService");
		Assert.assertTrue(indexService != null);
	}

}
