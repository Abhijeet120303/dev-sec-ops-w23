package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepository userRepo;

	public LoginResponse validateUser(LoginRequest loginRequest) {
		LoginResponse response = new LoginResponse();

		List<Users> receivedData = userRepo.findByUsername(loginRequest.getUsername());

		if (receivedData.size() == 1) {

			Users userData = receivedData.get(0);

			String encryptedPassword = userData.getPassword();

			String aesKey = userData.getAesKey();

			String decryptPassword = decryptPassword(encryptedPassword, aesKey);
			String requestPassword = loginRequest.getPassword();

			if (requestPassword.equalsIgnoreCase(decryptPassword)) {
				response.setCode(ResponseCode.LOGIN_SUCCESS.getCode());
				response.setStatus(ResponseCode.LOGIN_SUCCESS.getStatus());
				response.setMessage(ResponseCode.LOGIN_SUCCESS.getMessage());
			} else {
				response.setCode(ResponseCode.LOGIN_FAIL.getCode());
				response.setStatus(ResponseCode.LOGIN_FAIL.getStatus());
				response.setMessage(ResponseCode.LOGIN_FAIL.getMessage());
			}
		} else {
			response.setCode(ResponseCode.USERNAME_NOT_PRESENT_IN_DATABASE.getCode());
			response.setStatus(ResponseCode.USERNAME_NOT_PRESENT_IN_DATABASE.getStatus());
			response.setMessage(ResponseCode.USERNAME_NOT_PRESENT_IN_DATABASE.getMessage());
		}

		return response;
	}

	public String decryptPassword(String encryptPassword, String aesKey) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			SecretKey secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptPassword));
			return new String(decryptedBytes, StandardCharsets.UTF_8);
		} catch (Exception e) {

			throw new RuntimeException("Error decrypting card number", e);
		}
	}

}
