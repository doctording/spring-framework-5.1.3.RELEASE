package com.mb.dependencyaop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/2 12:49
 *
 * 切面
 */
@Component
@Aspect
public class MyAspect {
	public MyAspect() {
		System.out.println("MyAspect create");
	}

	/**
	 * 任意方法的切点
	 */
	@Pointcut("execution(* com.mb.dependencyaop..*.*(..))")
	public void pointCutMethod() {
	}

	/**
	 * 增强器 after
	 */
	@After("pointCutMethod()")
	public void adviceAfter(){
		System.out.println("======adviceAfter");
	}
}
