package test.com.mb;

import com.mb.dependency.A;
import com.mb.dependency.B;
import com.mb.dependency.WeakA;
import com.mb.dependency.WeakB;
import com.mb.dependencyaop.DependencyAopConfig;
import com.mb.dependencyaop.M;
import com.mb.dependencyaop.Z;
import com.mb.service.ServiceConfig;
import com.mb.service.TransferService;
import com.mb.service.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author mubi
 * @Date 2020/6/10 22:22
 */
public class DependencyTest {

	@Test
	public void testDependency() {
		A a = new A();
		B b = new B();
		a.setB(b);
		b.setA(a);
		Assert.assertTrue(a.getB() != null);
		Assert.assertTrue(b.getA() != null);
	}

	/**
	 * A,B 都是 singleton bean
	 */
	@Test
	public void testDependencyXml() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-dependency.xml");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		Assert.assertTrue(a != null && a.getB() != null);
		Assert.assertTrue(b != null && b.getA() != null);
	}

	/**
	 * X,M 都是 singleton bean
	 */
	@Test
	public void testDependencyAop() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(DependencyAopConfig.class);
		Z z = (Z) applicationContext.getBean("z");
		M m = (M) applicationContext.getBean("m");
		Assert.assertTrue(z != null && z.getM() != null);
		Assert.assertTrue(m != null && m.getZ() != null);
		z.testAA();
	}

	/**
	 * A,B 都是 singleton bean
	 */
	@Test
	public void testDependencySpringAop() {
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-dependency-aop.xml");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		Assert.assertTrue(a != null && a.getB() != null);
		Assert.assertTrue(b != null && b.getA() != null);
	}

	/**
	 * nested exception is org.springframework.beans.factory.BeanCreationException
	 *
	 * 报错：Is there an unresolvable circular reference?
	 *
	 */
	@Test
	public void testDependencySpringPrototype() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-dependency-prototype.xml");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		Assert.assertTrue(a != null);
		Assert.assertTrue(b != null);
	}

	/**
	 * depends-on="weakB"
	 */
	@Test
	public void testDependencySpringWeak() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-dependency-weak.xml");
		WeakA a = (WeakA) applicationContext.getBean("weakA");
		WeakB b = (WeakB) applicationContext.getBean("weakB");
		Assert.assertTrue(a != null);
		Assert.assertTrue(b != null);
	}

	/**
	 * 自动装配测试
	 */
	@Test
	public void testAuto() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ServiceConfig.class);
		System.out.println("-------------------------");
		UserController userController = (UserController) applicationContext.getBean("userController");
		Assert.assertTrue(userController != null);
		userController.query(1);
		System.out.println("-------------------------");
		TransferService transferService = (TransferService) applicationContext.getBean("transferService");
		Assert.assertTrue(transferService != null);
		transferService.transfer();
		System.out.println("-------------------------");
	}

}