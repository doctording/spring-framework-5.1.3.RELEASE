package test;


import com.test.config.AppConfig;
import com.test.entity.User;
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

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = (UserService) ac.getBean("userService");
		// false userService 不是 UserServiceImpl，而是一个UserServiceImpl代理类，因为有aop
		logger.info("-------------------" + (userService instanceof UserServiceImpl));
		User user = userService.getUserById(1);
		logger.info("user:" + user);
	}
}
