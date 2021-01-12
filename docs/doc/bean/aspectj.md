# Aop 代理

## CGLIB代理初步认识

在使用`@Configuration`注解时发生了CGLIB代理，但是Config类是可以不用`@Configuration`的,而使用`@Configuration`注解能完成代理，这样在config类中`@Bean`只实例化一次

参见：[@Configuration](./configuration.md)

回顾下：在refresh方法中执行`invokeBeanFactoryPostProcessors(beanFactory);`是会执行`invokeBeanFactoryPostProcessors`的（一个是子类的扫描后置处理，一个是父类的后置处理）

会执行到`org.springframework.context.annotation.ConfigurationClassPostProcessor#postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)`方法,有重要的一句

```java
enhanceConfigurationClasses(beanFactory);
```

而`org.springframework.context.annotation.ConfigurationClassPostProcessor#enhanceConfigurationClasses`对全注解类完成了CGLIB代理

```java
public void enhanceConfigurationClasses(ConfigurableListableBeanFactory beanFactory) {
    Map<String, AbstractBeanDefinition> configBeanDefs = new LinkedHashMap<>();
    for (String beanName : beanFactory.getBeanDefinitionNames()) {
        BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
        if (ConfigurationClassUtils.isFullConfigurationClass(beanDef)) {
            if (!(beanDef instanceof AbstractBeanDefinition)) {
                throw new BeanDefinitionStoreException("Cannot enhance @Configuration bean definition '" +
                        beanName + "' since it is not stored in an AbstractBeanDefinition subclass");
            }
            else if (logger.isInfoEnabled() && beanFactory.containsSingleton(beanName)) {
                logger.info("Cannot enhance @Configuration bean definition '" + beanName +
                        "' since its singleton instance has been created too early. The typical cause " +
                        "is a non-static @Bean method with a BeanDefinitionRegistryPostProcessor " +
                        "return type: Consider declaring such methods as 'static'.");
            }
            configBeanDefs.put(beanName, (AbstractBeanDefinition) beanDef);
        }
    }
    if (configBeanDefs.isEmpty()) {
        // nothing to enhance -> return immediately
        return;
    }

    ConfigurationClassEnhancer enhancer = new ConfigurationClassEnhancer();
    for (Map.Entry<String, AbstractBeanDefinition> entry : configBeanDefs.entrySet()) {
        AbstractBeanDefinition beanDef = entry.getValue();
        // If a @Configuration class gets proxied, always proxy the target class
        beanDef.setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
        try {
            // Set enhanced subclass of the user-specified bean class
            Class<?> configClass = beanDef.resolveBeanClass(this.beanClassLoader);
            if (configClass != null) {
                Class<?> enhancedClass = enhancer.enhance(configClass, this.beanClassLoader);
                if (configClass != enhancedClass) {
                    if (logger.isTraceEnabled()) {
                        logger.trace(String.format("Replacing bean definition '%s' existing class '%s' with " +
                                "enhanced class '%s'", entry.getKey(), configClass.getName(), enhancedClass.getName()));
                    }
                    beanDef.setBeanClass(enhancedClass);
                }
            }
        }
        catch (Throwable ex) {
            throw new IllegalStateException("Cannot load configuration class: " + beanDef.getBeanClassName(), ex);
        }
    }
}
```

## 附：代理技术

* Spring AOP 属于运行时增强，而 AspectJ 是编译时增强
* Spring AOP 基于代理(Proxying)，而 AspectJ 基于字节码操作(Bytecode Manipulation)

附：动态字节码生成代理

```java
       编译                     ClassLoader加载                 实例
        
.java ------> .class(字节码)  -------------------> Class Obj ------------> Class Instance
```

动态代理主要的几种技术
1. Java Proxy（接口&反射机制，新增一个完整的class字节码）
2. CGLib（父类继承，新增一个完整的class字节码）
3. AspectJ（修改现有字节码）
4. JavaAgent（修改现有字节码）

|类别 | 机制 | 原理 | 优点| 缺点| 技术|
|:----:|:----:|:----:|:----:|:----:|:----:|
|静态AOP|静态织入|在编译期,切面直接以字节码的形式编译到目标字节码文件中 | 对系统无性能影响 | 灵活性不够 |AspectJ|
|动态AOP |动态代理 |在运行期,目标类加载后，为接口动态生成代理类，将切面植入到代理类中 |相对于静态AOP更加灵活 | 切入的关注点需要实现接口。对系统有一点性能影响 |JDK dynamic proxy|
|动态字节码生成 | 在运行期 | 目标类加载后，动态构建字节码文件生成目标类的子类，将切面逻辑加入到子类中 | 没有接口也可以织入 |扩展类的实例方法为final时，则无法进行织入 | cglib |
|自定义类加载器 | 在运行期 | 目标加载前，将切面逻辑加到目标字节码里 | 可以对绝大部分类进行织入 | 代码中如果使用了其他类加载器，则这些类将不会被织入 | - |
| 字节码转换 | 在运行期| 所有类加载器加载字节码前，前进行拦截 | 可以对所有类进行织入 | - | - |
