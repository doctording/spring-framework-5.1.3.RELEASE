package com.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author mubi
 * @Date 2020/11/4 23:38
 */
@Component
@Aspect
public class SystemLogAspect {

	// Controller层切点
	@Pointcut("execution(* com.test.controller..*.*(..))")
	public void controllerAspect() {
	}

	@After("controllerAspect()")
	public void afterMethod(JoinPoint joinPoint) {
		System.out.println("=====controller after通知开始=====");
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class<?>[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						operationType = method.getAnnotation(OperateLog.class).operationType();
						operationName = method.getAnnotation(OperateLog.class).operationName();
						break;
					}
				}
			}
			//*========控制台输出=========*//
			System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("方法类型:" + operationType);
			System.out.println("方法描述:" + operationName);
		}catch (Exception e){

		}
	}

	@Around(value = "@within(com.test.aop.HttpLogger) ||" + "@annotation(com.test.aop.HttpLogger)")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		System.out.println("=====HttpLogger around通知开始=====");
		// 获取开始时间
		Long startTime = System.currentTimeMillis();
		// result的值就是被拦截方法的返回值
		Object result = pjd.proceed();
		Long processTime = System.currentTimeMillis() - startTime;
		System.out.println("aroundMethod return:" + result);
		System.out.println("aroundMethod processTime:" + processTime);
		return result;
	}
}
