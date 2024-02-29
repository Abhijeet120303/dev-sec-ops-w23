package org.dnyanyog.entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Component
@Table
public class Customer {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private long Id;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String age;
	
	public static Customer getInstance() {
		return new Customer();
	}

	public long getId() {
		return Id;
	}

	public Customer setId(long id) {
		Id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;

	}

	public String getPassword() {
		return password;
	}

	public Customer setPassword(String password) {
		this.password = password;
		return this;

	}

	public String getEmail() {
		return email;
	}

	public Customer setEmail(String email) {
		this.email = email;
		return this;

	}

	public String getAge() {
		return age;
	}

	public Customer setAge(String age) {
		this.age = age;
		return this;

	}
	
	
	
	
	
	
	
	
	
}