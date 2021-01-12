package com.mb.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @Author mubi
 * @Date 2020/11/22 20:57
 */
public class MailSendEvent extends ApplicationContextEvent {

	private static final long serialVersionUID = 1L;

	private String to;  //目的地

	public MailSendEvent(ApplicationContext source, String to) {
		super(source);
		this.to = to;
	}

	public String getTo(){
		return this.to;
	}
}