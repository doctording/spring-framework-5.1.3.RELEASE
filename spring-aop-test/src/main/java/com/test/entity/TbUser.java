package com.test.entity;

/**
 * @Author mubi
 * @Date 2020/7/2 09:24
 */
public class TbUser {
	private Integer id;
	private String sno;
	private String name;
	private String password;

	public TbUser() {
	}

	public TbUser(Integer id, String sno, String name, String password) {
		this.id = id;
		this.sno = sno;
		this.name = name;
		this.password = password;
	}

	public TbUser(String sno, String name, String password) {
		this.sno = sno;
		this.name = name;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TbUser{" +
				"id=" + id +
				", sno='" + sno + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
