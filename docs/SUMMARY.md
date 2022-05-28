# Summary

* [前言](doc/target.md)
* [ClassPathXmlApplicationContext Demo 引入]
    * [2.1 ApplicationContext执行流程](doc/test/flow_path.md)
        * [2.2 执行流程:refresh的12个方法](doc/test/code_review.md))
    * [2.3 bean & constructor-arg测试](doc/test/bean_constructor.md)
* [Ioc & Aop]
    * [Ioc & Bean]
        * [Ioc & BeanDefinition](doc/bean/ioc_bean.md)
            * [BeanFactory](doc/bean/BeanFactory.md)
            * [BeanFactory & ApplicationContext](doc/bean/context.md)
            * [事件发布和监听机制](doc/bean/context_event.md)
        * [Bean]
            * [Bean生命周期&回调](doc/bean/bean_life.md)
                * [Bean的作用域](doc/bean/bean_scope.md)
                * [FactoryBean接口](doc/bean/fatory_bean.md)
                * [懒加载bean](doc/bean/lazy.md)
            * [Bean 依赖](doc/bean/ioc_bean_dependency.md)
                * [Spring循环依赖具体执行流程](doc/bean/dependency.md)
            * [BeanFactoryPostProcessor & BeanDefinitionRegistryPostProcessor](doc/bean/post_processor.md)
                * [PropertyPlaceholderConfigurer](doc/bean/property.md)
                * [@Bean](doc/bean/bean.md)
                * [@Configuration](doc/bean/configuration.md)
                * [@Configuration & @Bean作用同一个类](doc/bean/configuration_bean.md)
                * [@MapperScan 原理](doc/bean/mapper_scan.md)
            * [BeanPostProcessor](doc/bean/bean_post_processor.md)
                * [推断构造方法](doc/bean/bean_constructor.md)
            * [自动装配 & @Autowired](doc/bean/ioc_bean_auto.md)
    * [AOP]
        * [Spring AOP](doc/bean/aop.md)
        * [CGLIB & AOP代理](doc/bean/aspectj.md)
* [Spring 事务](doc/bean/transaction.md)
    * [@Transactional](doc/bean/transaction_annotation.md)  
* [MyBatis手写](doc/mybatis.md)
    * [MyBatis源码分析](doc/mybatis_source.md)
* [SpringMvc & SpringBoot](doc/springboot.md)
    * [SpringBoot启动&starter](doc/springboot_starter.md)
* [其它问题总结]
    * [7.1 Spring中的设计模式](doc/bean/design.md)
    * [7.2 Spring中常见问题](doc/pro.md)
* [其它]
    * [8.1 单词](./doc/other/words.md)
