package test.com.mb;

import com.mb.life.BaseService;
import com.mb.life.Car;
import com.mb.life.ISomeService;
import com.mb.life.MainConfigLife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 15:43
 */
public class BeanLifeAnnotationTest {

	@Test
	public void testAnnotationConfigApplicationContextBeanLife() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(MainConfigLife.class);
		System.out.println("----------------applicationContext");
		BaseService baseService = (BaseService) applicationContext.getBean(ISomeService.class.getSimpleName());
		System.out.println("=======" + baseService.doSomething());
		System.out.println("=======" + baseService.getClass());
		((AnnotationConfigApplicationContext) applicationContext).close();
	}

}
