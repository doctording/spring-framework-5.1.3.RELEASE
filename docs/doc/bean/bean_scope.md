# Bean Scope

<a href="https://docs.spring.io/spring/docs/5.0.7.BUILD-SNAPSHOT/spring-framework-reference/html5/core.html#beans-factory-scopes" target="_blank">bean scope doc</a>

| scope | description | - |
| :----: | :----: | :----:  |
| singletion | (Default) Scopes a single bean definition to a single object instance per Spring IoC container. | 单例bean |
| prototype | Scopes a single bean definition to any number of object instances. | 多例bean,每次请求都新产生bean |
| request | Scopes a single bean definition to the lifecycle of a single HTTP request; that is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring ApplicationContext. | 每一次HTTP请求都会产生一个新的bean，该bean仅在当前HTTP request内有效 |
| session | Scopes a single bean definition to the lifecycle of an HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext. |  每一次HTTP请求都会产生一个新的 bean，该bean仅在当前 HTTP session 内有效 |
| application | Scopes a single bean definition to the lifecycle of a ServletContext. Only valid in the context of a web-aware Spring ApplicationContext. | - |
| websocket | Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware Spring ApplicationContext. | - |

## Spring 中的 singleton bean 的线程安全问题？

存在线程问题;

* 对于一个无状态Bean(即对bean属，性只有读操作，无写操作)，那么这个单例Bean是线程安全的。比如Spring mvc 的 Controller、Service、Dao等，这些Bean大多是无状态的，只关注于方法本身。

* 对于有状态的bean，Spring官方提供的bean，一般提供了通过`ThreadLocal`去解决线程安全的方法，比如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等。

## prototype

* Spring does not manage the complete lifecycle of a prototype bean

```java
@Component("userService")
@Scope("prototype")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUserById(Integer id){
		User user = userRepo.getById(id);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
```

* AbstractBeanFactory类的`doGetBean`方法


```java
else if (mbd.isPrototype()) {
    // It's a prototype -> create a new instance.
    Object prototypeInstance = null;
    try {
        beforePrototypeCreation(beanName);
        prototypeInstance = createBean(beanName, mbd, args);
    }
    finally {
        afterPrototypeCreation(beanName);
    }
    bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
}
```


总结：多线程, 生成多个实例，Ioc容器不负责完整的生命周期
