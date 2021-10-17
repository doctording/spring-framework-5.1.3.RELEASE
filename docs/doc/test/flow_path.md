# 测试代码执行流程

## 测试代码&xml

* code

见：`spring-framework-5.1.3.RELEASE/spring-context/src/test/java/test/com/mb/BeanTest.java`

```java
@Test
public void testClassPathXmlApplicationContextBeanCosr() {
    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring-constructor.xml");
    User user = (User) applicationContext.getBean("user");
    Assert.assertTrue(user != null);
    Assert.assertTrue(user.getTestStr().equals("testStr"));
    Assert.assertTrue(user.getId().equals(1));
    Assert.assertTrue(user.getName().equals("tom"));
}
```

* spring-constructor.xml

```xml
<bean id="user" class="com.mb.User">
    <constructor-arg index="0" value="1"/>
    <constructor-arg index="1" value="tom"/>
</bean>
```

## 运行大致流程图

![](../../imgs/spring_frame2.png)
