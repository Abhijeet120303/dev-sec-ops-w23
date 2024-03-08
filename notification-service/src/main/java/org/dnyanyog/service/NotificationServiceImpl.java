package org.dnyanyog.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.dnyanyog.dto.request.NotificationRequest;
import org.dnyanyog.dto.response.NotificationResponse;
import org.dnyanyog.entity.Notification;
import org.dnyanyog.enums.EmailConstant;
import org.dnyanyog.enums.NotificationResponseCode;
import org.dnyanyog.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository repo;

	public NotificationResponse sendEmail(NotificationRequest request) {
		NotificationResponse response = new NotificationResponse();
		
		 if (request.getMode() == null || request.getSubject() == null || request.getBody() == null
	                || request.getTo() == null) {
	            return response
	                    .setStatus(NotificationResponseCode.INCOMPLETE_DATA.getStatus())
	                    .setCode(NotificationResponseCode.INCOMPLETE_DATA.getCode())
	                    .setMessage(NotificationResponseCode.INCOMPLETE_DATA.getMessage());
	        }
	        
	        try {
	        	EmailConstant.valueOf(request.getMode().toUpperCase());
	        } catch (IllegalArgumentException e) {
	            return response
	                    .setStatus(NotificationResponseCode.INVALID_MODE.getStatus())
	                    .setCode(NotificationResponseCode.INVALID_MODE.getCode())
	                    .setMessage(NotificationResponseCode.INVALID_MODE.getMessage());
	        }
	        
	        if (("EMAIL").equals(request.getMode()) && !isValidEmail(request.getTo())) {
	            return response
	                    .setStatus(NotificationResponseCode.INVALID_EMAIL.getStatus())
	                    .setCode(NotificationResponseCode.INVALID_EMAIL.getCode())
	                    .setMessage(NotificationResponseCode.INVALID_EMAIL.getMessage());
	        }

	        Notification notificationEntity = Notification.getInstance()
	                .setClient_id(request.getClientId())
	                .setBody(request.getBody())
	                .setMode(request.getMode())
	                .setSubject(request.getSubject())
	                .setFooter(request.getFooter())
	                .setFrom_email(request.getFrom())
	                .setTo_email(request.getTo())
	                .setCreated_date(LocalDateTime.now())
	                .setUpdated_date(LocalDateTime.now());

	        try {
	            notificationEntity = repo.save(notificationEntity);
	            sendEmail(request.getTo(), request.getSubject(), request.getBody(), request.getFooter());
	            return response
	                    .setStatus(NotificationResponseCode.SUCCESS_NOTIFICATION_SENT.getStatus())
	                    .setCode(NotificationResponseCode.SUCCESS_NOTIFICATION_SENT.getCode())
	                    .setMessage(NotificationResponseCode.SUCCESS_NOTIFICATION_SENT.getMessage())
	                    .setTimeStamp(notificationEntity.getCreated_date());
	        } catch (Exception e) {
	            e.printStackTrace();
	            return response
	                    .setStatus(NotificationResponseCode.ERROR_CATCH_BLOCK.getStatus())
	                    .setCode(NotificationResponseCode.ERROR_CATCH_BLOCK.getCode())
	                    .setMessage(NotificationResponseCode.ERROR_CATCH_BLOCK.getMessage());
	        }
	    }

	    private boolean isValidEmail(String email) {
	        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	    }

	    private void sendEmail(String recipientEmail, String subject, String body, String footer) throws IOException {
	        try {
	            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.config"));

	            Session session = Session.getInstance(properties, new Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(properties.getProperty("email.sender.address"),
	                            properties.getProperty("email.sender.password"));
	                }
	            });

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(properties.getProperty("email.sender.address")));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
	            message.setSubject(subject);
	            message.setText(body + "\n" + footer);
	            Transport.send(message);

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
