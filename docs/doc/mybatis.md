# MyBatis

## ORM概念

对象关系映射（Object Relational Mapping，简称ORM）是通过使用描述对象和数据库之间映射的元数据，将面向对象语言程序中的对象自动持久化到关系数据库中。本质上就是将数据从一种形式转换到另外一种形式。 这也同时暗示着额外的执行开销；然而，如果ORM作为一种中间件实现，则会有很多机会做优化，而这些在手写的持久层并不存在。 更重要的是用于控制转换的元数据需要提供和管理；但是同样，这些花费要比维护手写的方案要少；而且就算是遵守ODMG规范的对象数据库依然需要类级别的元数据。

## Jdbc传统做法

1. 导入jdbc驱动包
2. 通过DriverManager注册驱动
3. 创建连接
4. 创建statement
5. 执行curd sql语句
6. 操作结果集
6. 关闭连接

## Mybatis ORM框架

<a href="https://mybatis.org/mybatis-3/zh/index.html" target="_blank"> mybatis 官方文档</a>

<a href="https://mybatis.org/spring/" target="_blank">mybatis 结合 Spring 官方文档</a>

MyBatis原理：<font color='red'>Jdk动态代理产生Mapper的代理对象</font>

1. 解析sql语句
2. 执行sql(数据库连接)

结合Spring:<font color='red'>mybatis产生的代理对象怎么放到Spring容器中</font>

自己产生的对象(自己new,而不是spring new出来的)怎么交给Spring管理？

1. 单个bean: `@Bean`
2. 成千上万的bean: `@FactoryBean`（mybatis中的`MapperFactoryBean`）
3. 用spring的beanFactory注册bean: beanFactory.registerSingleton(Object)

### 自己实现MyBatis

#### Session的getMapper方法，生产Mapper代理对象

```java
package com.test.mybatis;

import java.lang.reflect.Proxy;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 */
public class DbSession {

	public static Object getMapper(Class<?> clazz) {
		Class<?>[] clazzs = new Class<?>[]{clazz};
		Object object = Proxy.newProxyInstance(DbSession.class.getClassLoader(),
				clazzs,
				new DbInvocationHandler());
		return object;
	}
}

```

#### Mapper方法的执行利用反射(1.解析sql,2.jdbc执行)

```java
package com.test.mybatis;

import com.test.entity.TbUser;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 */
public class DbInvocationHandler implements InvocationHandler {

	/**
	 * mapper 里面的方法，逻辑是一个
	 * 1. 解析sql
	 * 2. 执行sql（jdbc连接)
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 1. parse sql
		Select annotation = method.getAnnotation(Select.class);
		String sql = annotation.value()[0];
		Object val = args[0];
		String parseSql = sql.replace("#{id}", String.valueOf(val));
		System.out.println("parse sql:" + parseSql);
		// 2. execute sql
		return exeSql(parseSql);
	}

	static TbUser exeSql(String sql) {
		Connection conn = null;
		Statement stmt = null;
		Integer id = 0;
		String name = null;
		String sno = null;
		String password = null;
		try {
			//STEP 1: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 2: Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
					"root", "");
			//STEP 3: Execute a query
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 4: Get results
			while (rs.next()) {
				id = Integer.valueOf(rs.getString("id"));
				sno = rs.getString("sno");
				name = rs.getString("name");
				password = rs.getString("password");
				break;
			}
			rs.close();
		} catch (Exception e) {

		}//end try
		return new TbUser(id, sno, name, password);
	}

}
```

#### 利用`FactoryBean`获取成千上万的不同`Mapper`对象

```java
package com.test.mybatis;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 * FactoryBean是一个特殊bean: 自己 + 产生的bean
 * Spring能获取到该bean, 和该bean产生的bean
 */
public class DbMapperFactoryBean implements FactoryBean<Object> {

	Class<?> mapperInterface;

	@Override
	public Object getObject() throws Exception {
		Object object = DbSession.getMapper(mapperInterface);
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
```

#### ImportBeanDefinitionRegistrar 注册FactoryBean到Spring中

```java
package com.test.mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 * ImportBeanDefinitionRegistrar 动态的注册自己写的Bean, 这里是`DbMapperFactoryBean`
 */
public class DbImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DbMapperFactoryBean.class);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		// 这里可以利用反射，for循环mapper package下的所有Mapper类，然后添加所有
		beanDefinition.getConstructorArgumentValues()
			.addGenericArgumentValue("com.test.mapper.UserMapper");
		registry.registerBeanDefinition("dbMapperFactoryBean", beanDefinition);
	}
}
```

#### 支持MapperScan注解

```java
package com.test.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Import(DbImportBeanDefinitionRegistrar.class)
public @interface DbMapperScan{

}
```
