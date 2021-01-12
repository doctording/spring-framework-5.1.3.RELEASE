package com.mb.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/22 20:58
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {
	@Override
	public void onApplicationEvent(MailSendEvent mailSendEvent) {
		System.out.println("...onApplicationEvent");
		MailSendEvent event = mailSendEvent;
		System.out.println("MailSender向"+ event.getTo()+ "发送了邮件");
	}
}