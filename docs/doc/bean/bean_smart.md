# SmartInstantiationAwareBeanPostProcessor

```java
public interface SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor {

	/**
	 * Predict the type of the bean to be eventually returned from this
	 * processor's {@link #postProcessBeforeInstantiation} callback.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the raw class of the bean
	 * @param beanName the name of the bean
	 * @return the type of the bean, or {@code null} if not predictable
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	@Nullable
	default Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	/**
	 * Determine the candidate constructors to use for the given bean.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the raw class of the bean (never {@code null})
	 * @param beanName the name of the bean
	 * @return the candidate constructors, or {@code null} if none specified
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	@Nullable
	default Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
			throws BeansException {

		return null;
	}

	/**
	 * Obtain a reference for early access to the specified bean,
	 * typically for the purpose of resolving a circular reference.
	 * <p>This callback gives post-processors a chance to expose a wrapper
	 * early - that is, before the target bean instance is fully initialized.
	 * The exposed object should be equivalent to the what
	 * {@link #postProcessBeforeInitialization} / {@link #postProcessAfterInitialization}
	 * would expose otherwise. Note that the object returned by this method will
	 * be used as bean reference unless the post-processor returns a different
	 * wrapper from said post-process callbacks. In other words: Those post-process
	 * callbacks may either eventually expose the same reference or alternatively
	 * return the raw bean instance from those subsequent callbacks (if the wrapper
	 * for the affected bean has been built for a call to this method already,
	 * it will be exposes as final bean reference by default).
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the raw bean instance
	 * @param beanName the name of the bean
	 * @return the object to expose as bean reference
	 * (typically with the passed-in bean instance as default)
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	default Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
```


* 1 创建bean的过程中，实例化后会执行添加三级缓存的操作

```java
protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final @Nullable Object[] args)
			throws BeanCreationException {
 
		
		BeanWrapper instanceWrapper = null;
		//省略其他代码……
		//从beanWrapper中获取我们的早期对象（实例化后的对象）
		final Object bean = instanceWrapper.getWrappedInstance();
		
        //省略其他代码……
		
 
		
		//缓存单例到三级缓存中，以防循环依赖		
		boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
				isSingletonCurrentlyInCreation(beanName));
		//上述条件满足，允许中期暴露对象
		if (earlySingletonExposure) {
			
			//把我们的早期对象包装成一个singletonFactory对象 该对象提供了一个getObject方法,该方法内部调用getEarlyBeanReference方法
			addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
		}
 
		//省略其他代码……
	}
```

* 2 三级缓存是个lambda表达，一个ObjectFactory(对象工厂,用来生成早期对象，放到二级缓存中)

```java
addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
```

`addSingletonFactory`方法如下：

```java

protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
		Assert.notNull(singletonFactory, "Singleton factory must not be null");
		//同步加锁
		synchronized (this.singletonObjects) {
			//单例缓存池中没有包含当前的bean
			if (!this.singletonObjects.containsKey(beanName)) {
				//加入到三级缓存中，，，，，暴露早期对象用于解决循环依赖
				this.singletonFactories.put(beanName, singletonFactory);
				this.earlySingletonObjects.remove(beanName);
				this.registeredSingletons.add(beanName);
			}
		}
        ...
```

`getEarlyBeanReference`方法如下:

```java
protected Object getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
		Object exposedObject = bean;
		//判读我们容器中是否有InstantiationAwareBeanPostProcessors类型的后置处理器
		if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
			//获取我们所有的后置处理器
			for (BeanPostProcessor bp : getBeanPostProcessors()) {
				//判断我们的后置处理器是不是实现了SmartInstantiationAwareBeanPostProcessor接口
				if (bp instanceof SmartInstantiationAwareBeanPostProcessor) {
					//进行强制转换
					SmartInstantiationAwareBeanPostProcessor ibp = (SmartInstantiationAwareBeanPostProcessor) bp;
					//挨个调用SmartInstantiationAwareBeanPostProcessor的getEarlyBeanReference
					exposedObject = ibp.getEarlyBeanReference(exposedObject, beanName);
				}
			}
		}
		return exposedObject;

```

* 3 早期对象创建则是要对实例对象执行所有`SmartInstantiationAwareBeanPostProcessor`的getEarlyBeanReference方法

`AbstractAutoProxyCreator`是SmartInstantiationAwareBeanPostProcessor的实现类之一，其重写了getEarlyBeanReference方法

```java
@Override
public Object getEarlyBeanReference(Object bean, String beanName) {
    Object cacheKey = getCacheKey(bean.getClass(), beanName);
    if (!this.earlyProxyReferences.contains(cacheKey)) {
        this.earlyProxyReferences.add(cacheKey);
    }
    return wrapIfNecessary(bean, beanName, cacheKey);
}
```

* 4 getEarlyBeanReference 实现中的 getEarlyBeanReference 方法完成

```java
protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
    //已经被处理过（解析切面时targetSourcedBeans出现过） 就是自己实现创建动态代理逻辑
    if (StringUtils.hasLength(beanName) && this.targetSourcedBeans.contains(beanName)) {
        return bean;
    }
    //不需要增强的
    if (Boolean.FALSE.equals(this.advisedBeans.get(cacheKey))) {
        return bean;
    }
    //是不是基础的bean 是不是需要跳过的 重复判断 （ 因为循环依赖是可以改变bean的，如果把bean改成了advisor呢）
    if (isInfrastructureClass(bean.getClass()) || shouldSkip(bean.getClass(), beanName)) {
        this.advisedBeans.put(cacheKey, Boolean.FALSE);
        return bean;
    }

    // 根据当前bean找到匹配的advisor
    Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
    // 当前bean匹配到了advisor
    if (specificInterceptors != DO_NOT_PROXY) {
        // 标记为已处理
        this.advisedBeans.put(cacheKey, Boolean.TRUE);
        //创建我们的真正的代理对象
        Object proxy = createProxy(
                bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
        //加入到缓存
        this.proxyTypes.put(cacheKey, proxy.getClass());
        return proxy;
    }

    this.advisedBeans.put(cacheKey, Boolean.FALSE);
    return bean;
}
```

其最后返回的是增强后的bean对象

* 5 代理对象由aop创建(AbstractAutoProxyCreator的createProxy方法)

```java
/**
    * Create an AOP proxy for the given bean.
    * @param beanClass the class of the bean
    * @param beanName the name of the bean
    * @param specificInterceptors the set of interceptors that is
    * specific to this bean (may be empty, but not null)
    * @param targetSource the TargetSource for the proxy,
    * already pre-configured to access the bean
    * @return the AOP proxy for the bean
    * @see #buildAdvisors
    */
protected Object createProxy(Class<?> beanClass, @Nullable String beanName,
        @Nullable Object[] specificInterceptors, TargetSource targetSource) {

    if (this.beanFactory instanceof ConfigurableListableBeanFactory) {
        AutoProxyUtils.exposeTargetClass((ConfigurableListableBeanFactory) this.beanFactory, beanName, beanClass);
    }

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.copyFrom(this);

    if (!proxyFactory.isProxyTargetClass()) {
        if (shouldProxyTargetClass(beanClass, beanName)) {
            proxyFactory.setProxyTargetClass(true);
        }
        else {
            evaluateProxyInterfaces(beanClass, proxyFactory);
        }
    }

    Advisor[] advisors = buildAdvisors(beanName, specificInterceptors);
    proxyFactory.addAdvisors(advisors);
    proxyFactory.setTargetSource(targetSource);
    customizeProxyFactory(proxyFactory);

    proxyFactory.setFrozen(this.freezeProxy);
    if (advisorsPreFiltered()) {
        proxyFactory.setPreFiltered(true);
    }

    return proxyFactory.getProxy(getProxyClassLoader());
}
```
