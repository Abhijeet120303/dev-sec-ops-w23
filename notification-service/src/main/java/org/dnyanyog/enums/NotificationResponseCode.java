package org.dnyanyog.enums;

public enum NotificationResponseCode {
	
	SUCCESS_NOTIFICATION_SENT("0000","Success", "Notification sent successfully!"),
    INCOMPLETE_DATA("NOTI0001","Fail", "Incomplete data sent"),
    INVALID_MODE("NOTI0002","Fail", "Invalid notification mode"),
    INVALID_EMAIL("NOTI0003","Fail", "Invalid email address for To EMAIL"),
    ERROR_CATCH_BLOCK("NOTI0004","Fail", "Error occurred while saving or sending notification");

	
	private final String code;
    private final String status;
    private final String message;

    NotificationResponseCode(String code,String status, String message) {
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