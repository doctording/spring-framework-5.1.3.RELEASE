package com.test.aop;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
public class SelfAspect {

	private static final Log logger = LogFactory.getLog(SelfAspect.class);

	/**
	 * 针对dao的切点
	 */
	@Pointcut("execution(* com.test.dao..*.*(..))")
	public void pointCutDao() {
	}

	/**
	 * 增强器 after
	 */
	@After("pointCutDao()")
	public void adviceAfter() {
		logger.info("-------------aop advice after");
	}

	/**
	 * 针对service的切点
	 */
	@Pointcut("execution(* com.test.service..*.*(..))")
	public void pointCutService() {
	}

	/**
	 * 增强器,before
	 */
	@Before("pointCutService()")
	public void adviceBefore() {
		logger.info("-------------aop advice before");
	}

}
