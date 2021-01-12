package com.mb.methodinjection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/4 15:18
 */
@Component
public class CommandManager implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public CommandManager() {
		System.out.println("CommandManager construct");
	}

	/**
	 * CommandManager 需要一个依赖一个 `@Scope("prototype")`的 Command Bean, 但是自身又是 单例Bean
	 * 通过实现 ApplicationContextAware, 能获取到 CommandManager 所在的 ApplicationContext，然后再据此获取原型 Command Bean
	 */
	public String process(int commandState) {
		// grab a new instance of the appropriate Command
		Command command = createCommand();
		System.out.println("command:" + command);
		// set the state on the (hopefully brand new) Command instance
		command.setState(commandState);
		return command.execute();
	}

	protected Command createCommand() {
		// notice the Spring API dependency!
		return this.applicationContext.getBean("command", Command.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}