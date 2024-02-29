package org.dnyanyog.common;

public enum ResponseCode {

	LOGIN_SUCCESS("0000", "Success", "Login Successful"), 
	LOGIN_FAIL("0001", "Fail", "Username and Password does not match"),
	USERNAME_NOT_PRESENT_IN_DATABASE("0000","Success","Username Not Present"),
	USER_ADD_SUCCESSFULLY("0000", "Success", "User Add Sucessfully"),
	USER_SUCCESSFULLY_FOUND("0000", "Success", "User Successfully Found"),
	USER_NOT_FOUND("0001", "Fail", "User Not Found"),
	USER_SUCCESSFULLY_UPDATED("0000", "Success", "User Successfully Updated"),
	EMIL_ALREADY_EXIT("0001","Fail","Email Alreaday Registerd");

	private final String code;
	private final String status;
	private final String message;

	ResponseCode(String code, String status, String message) {
		
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	

}
