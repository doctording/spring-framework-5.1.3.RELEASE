package test.com.mb;

import com.mb.methodinjection.AwareConfig;
import com.mb.methodinjection.CommandManager;
import com.mb.methodinjection.CommandManager2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/4 13:21
 */
public class ApplicationAwareTest {

	@Test
	public void testDependencySpring() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(AwareConfig.class);
		CommandManager commandManager = (CommandManager) applicationContext.getBean("commandManager");
		String rs = commandManager.process(1);
		System.out.println("precess1-----" + rs);

		String rs2 = commandManager.process(2);
		System.out.println("precess2-----" + rs2);


		CommandManager2 commandManager2 = (CommandManager2) applicationContext.getBean("commandManager2");
		System.out.println("precessAware-----" +  commandManager2.process(11));

	}

}
