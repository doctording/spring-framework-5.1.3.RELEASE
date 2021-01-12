package com.mb.methodinjection;

import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/4 15:18
 */
@Component
public class CommandManager2 {

	public CommandManager2() {
		System.out.println("CommandManager2 construct");
	}

	/**
	 * CommandManager 需要一个依赖一个 `@Scope("prototype")`的 Command Bean, 但是自身又是 单例Bean
	 * 通过实现 ApplicationContextAware, 能获取到 CommandManager 所在的 ApplicationContext，然后再据此获取原型 Command Bean
	 */
	public String process(int commandState) {
		// grab a new instance of the appropriate Command
		Command command = (Command) BeanContextUtil.getBean("command");
		// set the state on the (hopefully brand new) Command instance
		command.setState(commandState);
		return command.execute();
	}

}