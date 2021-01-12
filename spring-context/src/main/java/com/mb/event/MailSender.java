package com.mb.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/22 20:59
 */
@Component
public class MailSender implements ApplicationContextAware {
	@Autowired
	private ApplicationContext applicationContext;  // 容器事件由容器触发

	public void sendMail(String to){
		System.out.println("...ApplicationContextAware");
		MailSendEvent event = new MailSendEvent(applicationContext, to);
		applicationContext.publishEvent(event);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
