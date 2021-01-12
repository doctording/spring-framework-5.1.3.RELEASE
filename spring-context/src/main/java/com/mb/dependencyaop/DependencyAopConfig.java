package com.mb.dependencyaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 */
@ComponentScan("com.mb.dependencyaop")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DependencyAopConfig {

}
