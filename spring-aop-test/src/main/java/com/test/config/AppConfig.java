package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author mubi
 * @Date 2020/7/2 09:17
 */
@ComponentScan("com.test")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
