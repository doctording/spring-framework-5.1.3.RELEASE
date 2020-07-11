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
