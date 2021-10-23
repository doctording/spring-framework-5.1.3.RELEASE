# 推断构造方法

bean实例化是根据构造函数来反射生成的，如何判断构造函数的生成则是在如下代码中

* org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance

```java
protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, @Nullable Object[] args) {
    // 解析class，确保class不为空，并且访问权限为public
    // Make sure bean class is actually resolved at this point.
    Class<?> beanClass = resolveBeanClass(mbd, beanName);

    if (beanClass != null && !Modifier.isPublic(beanClass.getModifiers()) && !mbd.isNonPublicAccessAllowed()) {
        throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                "Bean class isn't public, and non-public access not allowed: " + beanClass.getName());
    }
    // 配置的一种特殊的callback回调方法，通过这个callback创建bean
    Supplier<?> instanceSupplier = mbd.getInstanceSupplier();
    if (instanceSupplier != null) {
        return obtainFromSupplier(instanceSupplier, beanName);
    }
    // 通过工厂方法创建
    if (mbd.getFactoryMethodName() != null) {
        return instantiateUsingFactoryMethod(beanName, mbd, args);
    }

    // 一个类可能有多个构造器，所以Spring得根据参数个数、类型确定需要调用的构造器
    // 在使用构造器创建实例后，Spring会将解析过后确定下来的构造器或工厂方法保存在缓存中，避免再次创建相同bean时再次解析
    // Shortcut when re-creating the same bean...
    boolean resolved = false;
    boolean autowireNecessary = false;
    if (args == null) {
        synchronized (mbd.constructorArgumentLock) {
            if (mbd.resolvedConstructorOrFactoryMethod != null) {
                resolved = true;
                autowireNecessary = mbd.constructorArgumentsResolved;
            }
        }
    }
    if (resolved) {
        if (autowireNecessary) {
            // 有已经解析过class的构造器，使用已经解析好的构造器注入
            return autowireConstructor(beanName, mbd, null, null);
        }
        else {
            // 默认的构造器
            return instantiateBean(beanName, mbd);
        }
    }

    // 从BeanPostProcessor推断构造方法
    // 根据参数解析、确定构造函数，然后实例化
    // Candidate constructors for autowiring?
    Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
    if (ctors != null || mbd.getResolvedAutowireMode() == AUTOWIRE_CONSTRUCTOR ||
            mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args)) {
        //
        return autowireConstructor(beanName, mbd, ctors, args);
    }

    // Preferred constructors for default construction?
    ctors = mbd.getPreferredConstructors();
    if (ctors != null) {
        return autowireConstructor(beanName, mbd, ctors, null);
    }

    // No special handling: simply use no-arg constructor.
    return instantiateBean(beanName, mbd);
}
```

## 如何去确定到底用哪个构造方法?

1. 如果开发者指定了想要使用的构造方法，那么就用这个构造方法
2. 如果开发者没有指定想要使用的构造方法，则看开发者有没有让Spring自动去选择构造方法
3. 如果开发者也没有让Spring自动去选择构造方法，则Spring利用无参构造方法，如果没有无参构造方法，则报错

如何指定构造方法：

1. xml中的`<constructor-arg>`标签，这个标签表示构造方法参数，所以可以根据这个确定想要使用的构造方法的参数个数，从而确定想要使用的构造方法
2. 通过@Autowired注解，@Autowired注解可以写在构造方法上，所以哪个构造方法上写了@Autowired注解，表示开发者想使用哪个构造方法，当然，它和第一个方式的不同点是，通过xml的方式，我们直接指定了构造方法的参数值，而通过@Autowired注解的方式，需要Spring通过byType+byName的方式去找到符合条件的bean作为构造方法的参数值

## xml构造函数的配置

```java
<bean id="user" class="com.mb.User">
    <constructor-arg index="0" value="1"/>
</bean>
```

```java
<bean id="user" class="com.mb.User">
    <constructor-arg index="0" value="1"/>
    <constructor-arg index="1" value="tom"/>
</bean>
```
