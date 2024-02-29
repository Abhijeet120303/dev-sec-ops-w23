package org.dnyanyog.common;

public enum ResponseCode {

	ADD_CUSTOMER_SUCCESS("Success", "Customer Add Successfully"),
	ADD_CUSTOMER_DUPLICATE_EMAIL("Fail", "Email Already exit"),
	CUSTOMER_SUCCESSFULLY_FOUND("Success", "Customer Successfully Found"),
	CUSTOMER_NOT_FOUND("Fail", "Customer Not Found"),
	CUSTOMER_SUCCESSFULLY_UPDATED("Success", "Customer Successfully Updated"),
	CUSTOMER_SUCCESSFULLY_DELETED("Success", "Customer Successfully Deleted");
	;

	private final String status;
	private final String message;

	ResponseCode(String status, String message) {

		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
