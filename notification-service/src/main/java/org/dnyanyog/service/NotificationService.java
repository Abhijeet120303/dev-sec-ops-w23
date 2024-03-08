package org.dnyanyog.service;

import org.dnyanyog.dto.request.NotificationRequest;
import org.dnyanyog.dto.response.NotificationResponse;

public interface NotificationService {
	
	public NotificationResponse sendEmail(NotificationRequest request);

}
