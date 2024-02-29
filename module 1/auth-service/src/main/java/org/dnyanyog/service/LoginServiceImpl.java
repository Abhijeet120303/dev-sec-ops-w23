package org.dnyanyog.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
				response.setStatus("Success");
				response.setMessage("Login successful");
			} else {
				response.setStatus("Fail");
				response.setMessage("Username & Password Do Not Match");
			}
		} else {
			response.setStatus("Fail");
			response.setMessage("Request Username is Not present in the database");
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
