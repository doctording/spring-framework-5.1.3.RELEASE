package test.com.bfpp;

import com.bfpp.A;
import com.bfpp.B;
import com.bfpp.Config;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 15:43
 */
public class BfppTest {

	@Test
	public void test() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(Config.class);
		System.out.println("----------------applicationContext");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		System.out.println("===a:" + a.getIp());
		System.out.println("===b:" + b.getIp());
	}

}
