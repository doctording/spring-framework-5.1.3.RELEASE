import com.test.config.XyzConfig;
import com.test.entity.X;
import com.test.entity.Y;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/10 13:26
 */
public class TestXyz {

	@Test
	public void testXyzConfig(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(XyzConfig.class);
		XyzConfig config = ac.getBean(XyzConfig.class);
		System.out.println(config.getClass());
		X x = (X)ac.getBean("x");
		Y y = (Y)ac.getBean("y");
		Assert.assertTrue(x != null);
		Assert.assertTrue(y != null);
	}

}
