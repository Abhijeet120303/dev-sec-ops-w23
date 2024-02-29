package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class CustomerRequest {

	private String name;

	private String password;

	private String email;

	private String age;

	private long Id;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

}
