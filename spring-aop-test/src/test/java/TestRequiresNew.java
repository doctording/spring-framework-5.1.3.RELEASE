import com.test.config.TxConfig;
import com.test.propagation.requiresnew.RequiresNewService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/7/10 13:26
 */
public class TestRequiresNew {

	@Test
	public void testRequiresNew(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiresNewService requiresNewService = (RequiresNewService) ac.getBean("requiresNewService");
		requiresNewService.transactionExceptionRequiresNew();
	}

	@Test
	public void testRequiresNewExceptionInner(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiresNewService requiresNewService = (RequiresNewService) ac.getBean("requiresNewService");
		requiresNewService.transactionExceptionRequiresNewInner();
	}

	@Test
	public void testTransactionExceptionRequiresNewException(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiresNewService requiresNewService = (RequiresNewService) ac.getBean("requiresNewService");
		requiresNewService.transactionExceptionRequiresNewException();
	}

	@Test
	public void testTransactionExceptionRequiresNewException2(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiresNewService requiresNewService = (RequiresNewService) ac.getBean("requiresNewService");
		requiresNewService.transactionExceptionRequiresNewException2();
	}

	@Test
	public void testTransactionExceptionRequiresNewExceptionRuntime(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		RequiresNewService requiresNewService = (RequiresNewService) ac.getBean("requiresNewService");
		requiresNewService.transactionExceptionRequiresNewExceptionRuntime();
	}

}
