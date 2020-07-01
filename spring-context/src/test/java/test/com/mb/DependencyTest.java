package test.com.mb;

import com.mb.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
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
	public void testDependencySpring() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("spring-dependency.xml");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		Assert.assertTrue(a != null && a.getB() != null);
		Assert.assertTrue(b != null && b.getA() != null);
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

}