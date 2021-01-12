package com.mb.methodinjection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/4 15:41
 */
@Component
@Scope("prototype")
public class Command {

	int state;

	public Command() {
		System.out.println("Command construct");
	}

	public void setState(int state) {
		this.state = state;
	}

	public String execute(){
		return "Command state:" + state;
	}
}
