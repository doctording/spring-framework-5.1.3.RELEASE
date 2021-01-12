package com.mb.event;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author mubi
 * @Date 2020/11/22 21:02
 */
@ComponentScan("com.mb.event")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MailConfig {
}
