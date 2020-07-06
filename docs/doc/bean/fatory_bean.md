# FactoryBean

<a href="https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-factory-extension-factorybean" target="_blank">Customizing Instantiation Logic with a FactoryBean</a>

## FactoryBean 官方文档阅读

功能：使用`FactoryBean`定制bean的实例化逻辑

The FactoryBean interface is a point of pluggability into the Spring IoC container’s instantiation logic. If you have complex initialization code that is better expressed in Java as opposed to a (potentially) verbose amount of XML, you can create your own FactoryBean, write the complex initialization inside that class, and then plug your custom FactoryBean into the container.

The FactoryBean interface provides three methods:

1. Object getObject(): Returns an instance of the object this factory creates. The instance can possibly be shared, depending on whether this factory returns singletons or prototypes.

2. boolean isSingleton(): Returns true if this FactoryBean returns singletons or false otherwise.

3. Class getObjectType(): Returns the object type returned by the getObject() method or null if the type is not known in advance.

## FactoryBean 实践

* 实现一个`FactoryBean`


```java
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author mubi
 * @Date 2020/7/4 13:01
 */
public class MyGoFactoryBean implements FactoryBean<Go> {

	private String type;

	private Go getDefaultGo(){
		return new Go() {
			@Override
			public void out() {
				System.out.println("just go on foot");
			}
		};
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Go getObject(){
		if (type == null) {
			return getDefaultGo();
		}
		if (type.equalsIgnoreCase(GoEnum.BIKE.getType())) {
			return new BikeGo();
		}
		if (type.equalsIgnoreCase(GoEnum.CAR.getType())) {
			return new CarGo();
		}
		return getDefaultGo();
	}

	@Override
	public Class<Go> getObjectType() { return Go.class ; }

	@Override
	public boolean isSingleton() { return false; }
}
```

* xml

```java
<bean id="go" class="com.mb.factory.MyGoFactoryBean">
    <property name="type" value="car"></property>
</bean>
```

* 测试

```java
@Test
public void testDependencySpring() {
    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring-factory-bean.xml");
    Go go = (Go) applicationContext.getBean("go");
    go.out();
}
```

