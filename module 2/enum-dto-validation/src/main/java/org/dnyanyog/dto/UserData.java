package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Component
public class UserData {

	@NotNull(message = "Username is manadatory")
	@NotBlank(message = "Username Should Not blank")
	@Size(min = 6, max = 20, message = "Validation error:Usernameshould be minimum 6 to characters")
	private String username;

	
	@NotNull(message = "Password is manadatory")
	@NotBlank(message = "Password Should Not blank")
	@Size(min = 6, max = 20, message = "Validation error:Usernameshould be minimum 6 to characters")
	private String password;

	@NotNull(message = "Email is manadatory")
	@NotBlank(message = "Email Should Not blank")
	@Email(message = "Enter valid Email")
	private String email;

	@NotNull(message = "Age is manadatory")
	@NotBlank(message = "Age Should Not blank")
	@Positive(message = "Enter Positive Integer Age")
	private String age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
