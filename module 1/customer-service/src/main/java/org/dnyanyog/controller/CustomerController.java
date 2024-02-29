package org.dnyanyog.controller;

import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.entity.Customer;
import org.dnyanyog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping(path = "/api/v1/customer/add", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest) {
		return customerService.addCustomer(customerRequest);
	}

	@DeleteMapping(path = "/api/v1/customer/delete/{id}", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public CustomerResponse deleteCustomer(@PathVariable Long id) {
		return customerService.deleteCustomer(id);

	}

	@GetMapping(path = "/api/v1/customer/search/{id}")
	public CustomerResponse getSingleCustomer(@PathVariable Long id) {
		return customerService.getSingleCustomer(id);

	}

	@PostMapping(path = "/api/v1/customer/update/{id}")
	public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody Customer request) {
		return customerService.updateCustomer(id, request);

	}

}
