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
		System.out.println("User()");
	}

	public User(Integer id) {
		System.out.println("User(id)");
		this.id = id;
	}

	public User(Integer id, String name) {
		System.out.println("User(id, name)");
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
		return "User{" +
				"testStr='" + testStr + '\'' +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}

}
