package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Component
public class CustomerRequest {

	@NotNull(message = "Name is manadatory")
	@NotBlank(message = "Name Should Not blank")
	@Size(min = 3, max = 20, message = "Validation error:Name should be minimum 3 to 20  characters")
	private String name;

	@NotNull(message = "Password is manadatory")
	@NotBlank(message = "Password Should Not blank")
	@Size(min = 6, max = 20, message = "Validation error:Name should be minimum 6 to 20  characters")
	private String password;

	@NotNull(message = "Email is manadatory")
	@NotBlank(message = "Email Should Not blank")
	@Email(message = "Enter valid Email")
	private String email;

	@NotNull(message = "Age is manadatory")
	@NotBlank(message = "Age Should Not blank")
	@Positive(message = "Enter Positive Integer Age")
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
