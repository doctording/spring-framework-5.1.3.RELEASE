package test;


import com.test.config.TxConfig;
import com.test.entity.TbUser;
import com.test.propagation.required.RequiredService;
import com.test.service.UserService;
import com.test.service.impl.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/2 09:07
 */
public class TestApp {
	private static final Log logger = LogFactory.getLog(TestApp.class);

	void test(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = (UserService) ac.getBean("userService");
		// false userService 不是 UserServiceImpl，而是一个UserServiceImpl代理类，因为有aop
		logger.info("-------------------" + (userService instanceof UserServiceImpl));
		TbUser tbUser = userService.getTbUserById(1);
		logger.info("get tbUser:" + tbUser);

		TbUser tbUser2 = new TbUser("2", "tom", "123456");
		Boolean b = userService.insertAUser(tbUser2);
		logger.info("insert tbUser:" + tbUser2 + " " + b);
	}

	public static void main(String[] args) {
		testRequireRequired();
	}

	static void testRequiresNew(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiredService requiredService = (RequiredService) ac.getBean("requiredService");
		requiredService.transactionExceptionRequired();
	}


	static void testRequire(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiredService requiredService = (RequiredService) ac.getBean("requiredService");
		requiredService.transactionExceptionRequired();
	}

	static void testRequireInner(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiredService requiredService = (RequiredService) ac.getBean("requiredService");
		requiredService.transactionExceptionInner();
	}

	static void testRequireRequired(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiredService requiredService = (RequiredService) ac.getBean("requiredService");
		requiredService.transactionExceptionRequiredRequired();
	}

	static void testRequireRequiredInner(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiredService requiredService = (RequiredService) ac.getBean("requiredService");
		requiredService.transactionExceptionRequiredRequiredInner();
	}
}
