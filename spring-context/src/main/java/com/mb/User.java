package com.mb;

/**
 * @Author mubi
 * @Date 2020/6/10 22:19
 */
public class User {
	private String testStr = "testStr";

	private Integer id;
	private String name;

	public User() {
		System.out.println("User create");
	}

	public User(Integer id, String name) {
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
}
