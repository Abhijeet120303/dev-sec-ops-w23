package org.dnyanyog.dto.response;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class NotificationResponse {

	private String status;

	private String message;

	private String code;

	private LocalDateTime timeStamp;
	
	public static NotificationResponse getInstance() {
		return new NotificationResponse();
	}

	public String getStatus() {
		return status;
	}

	public NotificationResponse setStatus(String status) {
		this.status = status;
		return this;

	}

	public String getMessage() {
		return message;
	}

	public NotificationResponse setMessage(String message) {
		this.message = message;
		return this;

	}

	public String getCode() {
		return code;
	}

	public NotificationResponse setCode(String code) {
		this.code = code;
		return this;

	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public NotificationResponse setTimeStamp(LocalDateTime localDateTime) {
		this.timeStamp = localDateTime;
		return this;

	}

}
