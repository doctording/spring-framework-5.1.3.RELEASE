package com.bfpp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/6/10 22:19
 */
@Component
public class UserConstruct {
	private String testStr = "testStr";

	private Integer id;
	private String name;


	public UserConstruct() {
		System.out.println("UserConstruct()");
	}


	public UserConstruct(@Qualifier Integer id) {
		System.out.println("UserConstruct(id)");
		this.id = id;
	}

	public UserConstruct(Integer id, String name) {
		System.out.println("UserConstruct(id, name)");
		this.id = id;
		this.name = name;
	}

	public String getTestStr() {
		return testStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserConstruct{" +
				"testStr='" + testStr + '\'' +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
