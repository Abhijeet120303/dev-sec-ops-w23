package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class CustomerResponse {

	private String status;

	private String message;

	private String name;

	private String password;

	private String eamil;

	private String age;

	private long id;

	public static CustomerResponse getInstance() {
		return new CustomerResponse();
	}

	public long getId() {
		return id;
	}

	public CustomerResponse setId(long id) {
		this.id = id;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public CustomerResponse setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public CustomerResponse setMessage(String message) {
		this.message = message;
		return this;

	}

	public String getName() {
		return name;
	}

	public CustomerResponse setName(String name) {
		this.name = name;
		return this;

	}

	public String getPassword() {
		return password;
	}

	public CustomerResponse setPassword(String password) {
		this.password = password;
		return this;

	}

	public String getEamil() {
		return eamil;
	}

	public CustomerResponse setEamil(String eamil) {
		this.eamil = eamil;
		return this;

	}

	public String getAge() {
		return age;
	}

	public CustomerResponse setAge(String age) {
		this.age = age;
		return this;

	}

}
