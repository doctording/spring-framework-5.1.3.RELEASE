package com.test.aop;

import java.lang.annotation.*;

/**
 * @Author mubi
 * @Date 2020/7/3 10:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CallLogger {
	String value() default "";
}
