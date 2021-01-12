package test.com.mb;

import com.mb.beanfactorypostprocessor.IndexService;
import com.mb.beanfactorypostprocessor.PostProcessorConfigLife;
import com.mb.factorybean.Go;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 13:21
 */
public class BeanFactoryPostProcessorTest {

	@Test
	public void testBeanFactoryPostProcessor() {
		ApplicationContext ac =
				new AnnotationConfigApplicationContext(PostProcessorConfigLife.class);
		IndexService indexService = (IndexService)ac.getBean("indexService");
		int a = indexService.func(10);
		Assert.assertTrue(a == 110);
		((AnnotationConfigApplicationContext) ac).close();
	}

}
