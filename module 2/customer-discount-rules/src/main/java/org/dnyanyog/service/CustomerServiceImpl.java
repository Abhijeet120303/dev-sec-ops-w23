package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.dto.OrderRequest;
import org.dnyanyog.dto.OrderResponse;
import org.dnyanyog.entity.Customer;
import org.dnyanyog.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repo;

	private CustomerResponse customerResponse;

	@Autowired
	public OrderResponse orderResponse;

	public CustomerResponse addCustomer(CustomerRequest customerRequest) {

		Optional<Customer> receivedData = repo.findByEmail(customerRequest.getEmail());

		if (receivedData.isEmpty()) {
			Customer tableData = Customer.getInstance().setName(customerRequest.getName())
					.setAge(customerRequest.getAge()).setEmail(customerRequest.getEmail())
					.setPassword(customerRequest.getPassword());

			tableData = repo.save(tableData);

			customerResponse = CustomerResponse.getInstance().setStatus(ResponseCode.ADD_CUSTOMER_SUCCESS.getStatus())
					.setMessage(ResponseCode.ADD_CUSTOMER_SUCCESS.getMessage()).setName(tableData.getName())
					.setAge(tableData.getAge()).setEamil(tableData.getEmail()).setPassword(tableData.getPassword())
					.setId(tableData.getId());
		} else {
			customerResponse = CustomerResponse.getInstance()
					.setStatus(ResponseCode.ADD_CUSTOMER_DUPLICATE_EMAIL.getStatus())
					.setMessage(ResponseCode.ADD_CUSTOMER_DUPLICATE_EMAIL.getMessage());
		}

		return customerResponse;

	}

	public CustomerResponse getSingleCustomer(Long id) {

		Optional<Customer> receivedData = repo.findById(id);

		if (receivedData.isPresent()) {
			Customer customer = receivedData.get();

			customerResponse = CustomerResponse.getInstance().setName(customer.getName())
					.setPassword(customer.getPassword()).setAge(customer.getAge()).setEamil(customer.getAge())
					.setId(customer.getId()).setMessage(ResponseCode.CUSTOMER_SUCCESSFULLY_FOUND.getMessage())
					.setStatus(ResponseCode.CUSTOMER_SUCCESSFULLY_FOUND.getStatus());

		} else {
			customerResponse = CustomerResponse.getInstance().setStatus(ResponseCode.CUSTOMER_NOT_FOUND.getStatus())
					.setMessage(ResponseCode.CUSTOMER_NOT_FOUND.getMessage());
		}

		return customerResponse;
	}

	public CustomerResponse deleteCustomer(Long id) {

		Optional<Customer> receivedData = repo.findById(id);

		if (receivedData.isPresent()) {
			repo.deleteById(id);
			customerResponse = CustomerResponse.getInstance()
					.setStatus(ResponseCode.CUSTOMER_SUCCESSFULLY_DELETED.getStatus())
					.setMessage(ResponseCode.CUSTOMER_SUCCESSFULLY_DELETED.getMessage());
		} else {
			customerResponse = CustomerResponse.getInstance().setStatus(ResponseCode.CUSTOMER_NOT_FOUND.getStatus())
					.setMessage(ResponseCode.CUSTOMER_NOT_FOUND.getMessage());

		}

		return customerResponse;

	}

	public CustomerResponse updateCustomer(Long userId, Customer request) {
		Optional<Customer> receivedData = repo.findById(userId);
		if (receivedData.isPresent()) {

			Customer customer = receivedData.get();

			customer.setName(request.getName());
			customer.setPassword(request.getPassword());
			customer.setAge(request.getAge());
			customer.setEmail(request.getEmail());

			customer = repo.save(customer);

			customerResponse = CustomerResponse.getInstance()
					.setStatus(ResponseCode.CUSTOMER_SUCCESSFULLY_UPDATED.getStatus())
					.setMessage(ResponseCode.CUSTOMER_SUCCESSFULLY_UPDATED.getMessage());

		} else {
			customerResponse = CustomerResponse.getInstance().setStatus(ResponseCode.CUSTOMER_NOT_FOUND.getStatus())
					.setMessage(ResponseCode.CUSTOMER_NOT_FOUND.getMessage());

		}
		return customerResponse;
	}

	public OrderResponse discount(OrderRequest orderRequest) {

		int totalDiscountPrice;
		int afterDiscountPrice;

		int price = 50000;

		int age = orderRequest.getAge();
		String gender = orderRequest.getGender();

		if (age < 30 && gender.equals("M")) {
			totalDiscountPrice = ((price * 10) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 10% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);

		} else if (age >= 30 && age < 60 && gender.equals("M")) {
			totalDiscountPrice = ((price * 5) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 5% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);
		} else if (age >= 60 && gender.equals("M")) {
			totalDiscountPrice = ((price * 15) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 15% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);
		}

		// For Female Customers Extra 5% Discount
		
		else if (age < 30 && gender.equals("F")) {
			totalDiscountPrice = ((price * 15) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 15% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);
		} else if (age >= 30 && age < 60 && gender.equals("F")) {
			totalDiscountPrice = ((price * 10) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 10% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);
		} else if (age >= 60 && gender.equals("F")) {
			totalDiscountPrice = ((price * 20) / 100);
			afterDiscountPrice = price - totalDiscountPrice;

			orderResponse.setStatus("Success");
			orderResponse.setMessage("Customer Has Applicable for 20% Discount");
			orderResponse.setPrice(price);
			orderResponse.setAfterDiscountPrice(afterDiscountPrice);
		} else {
			orderResponse.setStatus("Fail");
			orderResponse.setMessage("Invalid Request");
		}
		return orderResponse;
	}

}
