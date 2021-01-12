package test.com.mb;

import com.mb.event.MailConfig;
import com.mb.event.MailSender;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2020/6/10 22:22
 */
public class EventTest {

	@Test
	public void testEvent() {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(MailConfig.class);
		MailSender mailSender = (MailSender) applicationContext.getBean("mailSender");
		mailSender.sendMail("AAA");
	}

}
