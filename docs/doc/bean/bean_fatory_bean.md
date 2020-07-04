# BeanFactory & FactoryBean 的区别

<a href="https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-factory-extension-factorybean" target="_blank">Customizing Instantiation Logic with a FactoryBean</a>

## FactoryBean 官方文档阅读

功能：使用`FactoryBean`定制bean的实例化逻辑

The FactoryBean interface is a point of pluggability into the Spring IoC container’s instantiation logic. If you have complex initialization code that is better expressed in Java as opposed to a (potentially) verbose amount of XML, you can create your own FactoryBean, write the complex initialization inside that class, and then plug your custom FactoryBean into the container.

The FactoryBean interface provides three methods:

1. Object getObject(): Returns an instance of the object this factory creates. The instance can possibly be shared, depending on whether this factory returns singletons or prototypes.

2. boolean isSingleton(): Returns true if this FactoryBean returns singletons or false otherwise.

3. Class getObjectType(): Returns the object type returned by the getObject() method or null if the type is not known in advance.

## FactoryBean 实践
