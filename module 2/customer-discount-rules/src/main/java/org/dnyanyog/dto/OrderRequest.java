package org.dnyanyog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class OrderRequest {

	@NotNull(message = "Age is manadatory")
	@NotBlank(message = "Age Should Not blank")
	@Positive(message = "Enter Positive Integer Age")
	private int age;

	@NotNull(message = "Gender is manadatory")
	@NotBlank(message = "Gender Should Not blank")
	@Pattern(regexp = "^[MF]$", message = "Gender should be either 'M' or 'F'")
	private String gender;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
