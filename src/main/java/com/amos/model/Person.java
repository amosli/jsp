package com.amos.model;

import java.util.Date;

public class Person {
	private String name="amosli";
	private Integer age;
	private Date birthday;
	private Address address;
	public void setAddress(Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	public Person(String name) {
		this.name = name;
	}
	public Person(){
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
