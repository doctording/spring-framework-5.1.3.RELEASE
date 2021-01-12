package com.test.aop;

import java.lang.annotation.*;

/**
 * @Author mubi
 * @Date 2020/7/3 10:27
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

	// 要执行的操作类型比如：add操作
	String operationType() default "";

	// 要执行的具体操作比如：添加用户
	String operationName() default "";
}
