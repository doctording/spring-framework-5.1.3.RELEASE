package test.com.mb.life;

import com.mb.life.Car;
import com.mb.life.MainConfigLife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 15:43
 */
public class BeanLifeTest {

	@Test
	public void testClassPathXmlApplicationContextBean() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(MainConfigLife.class);
		System.out.println("----------------applicationContext");
		Car car = (Car)applicationContext.getBean("car");
		((AnnotationConfigApplicationContext) applicationContext).close();
	}

}
