import com.test.config.TxConfig;
import com.test.config.XyzConfig;
import com.test.controller.UserController;
import com.test.entity.TbUser;
import com.test.entity.User;
import com.test.entity.X;
import com.test.entity.Y;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/10 13:26
 */
public class TestAop {

	@Test
	public void testAopAnnotation() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		UserController userController = (UserController) ac.getBean("userController");
		User user = userController.getById(101);
		System.out.println("user:" + user);
		Assert.assertTrue(user != null);
		TbUser tbUser = userController.getTbUserById(1);
		System.out.println("tbUser:" + tbUser);
		Assert.assertTrue(tbUser != null);
	}

}
