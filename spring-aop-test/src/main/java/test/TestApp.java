package test;


import com.test.config.TxConfig;
import com.test.entity.TbUser;
import com.test.mapper.UserMapper;
import com.test.mybatis.DbSession;
import com.test.propagation.required.RequiredService;
import com.test.service.UserService;
import com.test.service.impl.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;

/**
 * @Author mubi
 * @Date 2020/7/2 09:07
 */
public class TestApp {
	private static final Log logger = LogFactory.getLog(TestApp.class);

	void test() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = (UserService) ac.getBean("userService");
		// false userService 不是 UserServiceImpl，而是一个UserServiceImpl代理类，因为有aop
		logger.info("-------------------" + (userService instanceof UserServiceImpl));
		TbUser tbUser = userService.getTbUserById(1);
		logger.info("get tbUser:" + tbUser);

		TbUser tbUser2 = new TbUser("2", "tom", "123456");
		Boolean b = userService.insertAUser(tbUser2);
		logger.info("insert tbUser:" + tbUser2 + " " + b);
	}

	static void testAnnotationMybatis(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = (UserService) ac.getBean("userService");
		// false userService 不是 UserServiceImpl，而是一个UserServiceImpl代理类，因为有aop
		logger.info("-------------------" + (userService instanceof UserServiceImpl));
		TbUser tbUser = userService.selectTbUserById(1);
		logger.info("selectTbUserById:" + tbUser);
	}

	public static void main(String[] args) throws Exception{
		testAnnotationMybatis();
//		testSelfMybatis();
	}

	static void testSelfMybatis(){
		UserMapper userMapper = (UserMapper) DbSession.getMapper(UserMapper.class);
		logger.info("selectTbUserById:" + userMapper.selectUserById(1));
	}

	static void testXmlMyBatis() throws Exception{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UserMapper mapper = session.getMapper(UserMapper.class);
			TbUser user = mapper.selectUserById(1);
			System.out.println("user:" + user);
		}
	}
}
