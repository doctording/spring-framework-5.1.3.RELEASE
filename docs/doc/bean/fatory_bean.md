# FactoryBean

<a href="https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-factory-extension-factorybean" target="_blank">Customizing Instantiation Logic with a FactoryBean</a>

## FactoryBean 官方文档阅读

功能：使用`FactoryBean`定制bean的实例化逻辑

The FactoryBean interface is a point of pluggability into the Spring IoC container’s instantiation logic. If you have complex initialization code that is better expressed in Java as opposed to a (potentially) verbose amount of XML, you can create your own FactoryBean, write the complex initialization inside that class, and then plug your custom FactoryBean into the container.

The FactoryBean interface provides three methods:

1. `Object getObject()`: Returns an instance of the object this factory creates. The instance can possibly be shared, depending on whether this factory returns singletons or prototypes.

2. `boolean isSingleton()`: Returns true if this FactoryBean returns singletons or false otherwise.

3. `Class getObjectType()`: Returns the object type returned by the getObject() method or null if the type is not known in advance.

首先FactoryBean它是一个Bean，但又不仅仅是一个Bean。它是一个能生产或修饰对象生成的工厂Bean，类似于设计模式中的工厂模式和装饰器模式。它能在需要的时候生产一个对象，且不仅仅限于它自身，它能返回任何Bean的实例。

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

* GoEnum

```java
public enum GoEnum {
	BIKE("bike"),
	CAR("car");

	String type;

	GoEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
```

* xml中定制bean

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

### 面试题：FactoryBean跟BeanFactory的区别？

怎么回答？

* FactoryBean是Spring提供的一个扩展点，适用于复杂的Bean的创建，比如Mybatis在跟Spring做整合时就用到了这个扩展点。并且FactoryBean所创建的Bean跟普通的Bean不一样。我们可以说FactoryBean是Spring创建Bean的另外一种手段。

* BeanFactory是Spring IOC容器的顶级接口，其实现类有`XMLBeanFactory`，`DefaultListableBeanFactory`以及`AnnotationConfigApplicationContext`等。BeanFactory为Spring管理Bean提供了一套通用的规范

```java
boolean containsBean(String beanName)

Object getBean(String)

Object getBean(String, Class)

Class getType(String name)

boolean isSingleton(String)

String[] getAliases(String name)
```

通过这些方法，可以方便地获取bean，对Bean进行操作和判断

---

FactoryBean是一个能生产或修饰对象生成的工厂Bean。一个Bean如果实现了FactoryBean接口，那么根据该Bean的名称获取到的实际上是`getObject()`返回的对象，而不是这个Bean自身实例，如果要获取这个Bean自身实例，那么需要在名称前面加上`&`符号。

```java
@Component
public class MyBean implements FactoryBean {
    private String message;
    public MyBean() {
        this.message = "通过构造方法初始化实例";
    }
    @Override
    public Object getObject() throws Exception {
        MyBean myBean = new MyBean();
        myBean.message = "通过FactoryBean.getObject()创建实例";
        // 这里并不一定要返回MyBean自身的实例，可以是其他任何对象的实例
        return myBean;
    }
    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }
    public String getMessage() {
        return message;
    }
}
```

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class FactoryBeanTest {
    @Autowired
    private ApplicationContext context;
    @Test
    public void test() {
        MyBean myBean1 = (MyBean) context.getBean("myBean");
        System.out.println("myBean1 = " + myBean1.getMessage());
        MyBean myBean2 = (MyBean) context.getBean("&myBean");
        System.out.println("myBean2 = " + myBean2.getMessage());
        System.out.println("myBean1.equals(myBean2) = " + myBean1.equals(myBean2));
    }
}
```

输出

```java
myBean1 = 通过FactoryBean.getObject()初始化实例
myBean2 = 通过构造方法初始化实例
myBean1.equals(myBean2) = false
```
