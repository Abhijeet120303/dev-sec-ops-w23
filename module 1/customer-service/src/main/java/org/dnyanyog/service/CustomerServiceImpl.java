package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.entity.Customer;
import org.dnyanyog.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repo;

	private CustomerResponse customerResponse;

	public CustomerResponse addCustomer(CustomerRequest customerRequest) {

		Optional<Customer> receivedData = repo.findByEmail(customerRequest.getEmail());

		if (receivedData.isEmpty()) {
			Customer tableData = Customer.getInstance().setName(customerRequest.getName())
					.setAge(customerRequest.getAge()).setEmail(customerRequest.getEmail())
					.setPassword(customerRequest.getPassword());

			tableData = repo.save(tableData);

			customerResponse = CustomerResponse.getInstance().setStatus("Success")
					.setMessage("Customer Add Successfully").setName(tableData.getName()).setAge(tableData.getAge())
					.setEamil(tableData.getEmail()).setPassword(tableData.getPassword()).setId(tableData.getId());
		} else {
			customerResponse = CustomerResponse.getInstance().setStatus("Fail")
					.setMessage("Email Already exit");
		}

		return customerResponse;

	}

	public CustomerResponse getSingleCustomer(Long id) {

		Optional<Customer> receivedData = repo.findById(id);

		if (receivedData.isPresent()) {
			Customer customer = receivedData.get();

			customerResponse = CustomerResponse.getInstance().setName(customer.getName())
					.setPassword(customer.getPassword()).setAge(customer.getAge()).setEamil(customer.getAge())
					.setId(customer.getId()).setMessage("Customer Found Successfully").setStatus("Success");

		} else {
			customerResponse = CustomerResponse.getInstance().setStatus("Fail").setMessage("Customer Not Found");
		}

		return customerResponse;
	}

	public CustomerResponse deleteCustomer(Long id) {

		Optional<Customer> receivedData = repo.findById(id);

		if (receivedData.isPresent()) {
			repo.deleteById(id);
			customerResponse = CustomerResponse.getInstance().setStatus("Success")
					.setMessage("Customer Delete Successfully");
		} else {
			customerResponse = CustomerResponse.getInstance().setStatus("Fail").setMessage("Customer Not Found");

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

			customerResponse = CustomerResponse.getInstance().setStatus("Success").setMessage("Customer  Updated");

		} else {
			customerResponse = CustomerResponse.getInstance().setStatus("Fail").setMessage("Customer Not Found");

		}
		return customerResponse;
	}

}
