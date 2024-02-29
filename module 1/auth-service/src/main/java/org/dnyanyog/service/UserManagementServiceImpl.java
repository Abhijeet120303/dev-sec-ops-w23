package org.dnyanyog.service;

import static java.util.Objects.nonNull;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.dto.DiscountRequest;
import org.dnyanyog.dto.DiscountResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private String aesKey;

	Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	@Autowired
	UsersRepository userRepo;

	@Autowired
	AddUserResponse userResponse;

	@Autowired
	private List<Long> userIds;

	public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) {

		aesKey = generateAESKey();

		Users usersTable = Users.getInstance().setUsername(request.getUsername()).setAge(request.getAge())
				.setEmail(request.getEmail()).setPassword(encryptPassword(request.getPassword(), aesKey))
				.setUserId(generateRandomUserId()).setAesKey(aesKey);

		usersTable = userRepo.save(usersTable);

		userResponse.setMessage("User added successfully");
		userResponse.setStatus("Success");
		userResponse.setUserId(usersTable.getUserId());
		userResponse.getUserData().setEmail(usersTable.getEmail());
		userResponse.getUserData().setUsername(usersTable.getUsername());
		userResponse.getUserData().setPassword(usersTable.getPassword());
		userResponse.getUserData().setAge(usersTable.getAge());

		return Optional.of(userResponse);
	}

	public AddUserResponse getSingleUser(Long userId) {
		Optional<Users> receivedData = userRepo.findByUserId(userId);

		if (receivedData.isPresent()) {
			Users user = receivedData.get();

			String encyptedPassword = user.getPassword();
			String aesKey = user.getAesKey();

			userResponse.setStatus("Success");
			userResponse.setMessage("User found");
			userResponse.setUserId(user.getUserId());
			userResponse.getUserData().setEmail(user.getEmail());
			userResponse.getUserData().setUsername(user.getUsername());
			userResponse.getUserData().setPassword(decryptPassword(encyptedPassword, aesKey));
			userResponse.getUserData().setAge(user.getAge());

		} else {
			userResponse.setStatus("Fail");
			userResponse.setMessage("User not found");
		}
		return userResponse;
	}

	public List<Users> getAllUser() {
		return userRepo.findAll();
	}

	public List<Long> getAllUserIds() {
		List<Users> users = userRepo.findAll();

		for (Users user : users) {
			if (nonNull(user)) {
				userIds.add(user.getUserId());
			}
		}
		return userIds;
	}

	private long generateRandomUserId() {

		int randomId = (int) (Math.random() * 900) + 100;
		return randomId;
	}

	public AddUserResponse updateUser(Long userID, Users request) {

		AddUserResponse userResponse = new AddUserResponse();
		Optional<Users> receivedData = userRepo.findByUserId(userID);
		if (receivedData.isPresent()) {

			aesKey = generateAESKey();

			Users user = receivedData.get();

			user.setUsername(request.getUsername());
			user.setPassword(encryptPassword(request.getPassword(), aesKey));
			user.setAge(request.getAge());
			user.setEmail(request.getEmail());
			user.setAesKey(aesKey);

			user = userRepo.save(user);

			userResponse.setStatus("Success");
			userResponse.setMessage("User Updated");
		} else {
			userResponse.setStatus("Fail");
			userResponse.setMessage("User Not Found");

		}
		return userResponse;
	}

	private String generateAESKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			SecretKey secretKey = keyGen.generateKey();
			byte[] encodedKey = secretKey.getEncoded();
			return Base64.getEncoder().encodeToString(encodedKey);
		} catch (Exception e) {
			throw new RuntimeException("Error generating AES key", e);
		}
	}

	private String encryptPassword(String input, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			throw new RuntimeException("Error encrypting with AES", e);
		}
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

	public DiscountResponse discountUser(DiscountRequest request) {

		DiscountResponse discountResponse = new DiscountResponse();

		int discount;

		int price = 5000;

		int age = request.getAge();

		if (age > 30 && request.getGender() == "M") {
			discount = ((price * 10) / 100);
			price = price - discount;

			discountResponse.setAfterDiscountPrice(price);

		}

		return discountResponse;
	}

}
