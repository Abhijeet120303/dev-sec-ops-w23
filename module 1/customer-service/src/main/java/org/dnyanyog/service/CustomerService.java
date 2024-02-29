package org.dnyanyog.service;

import org.dnyanyog.dto.CustomerRequest;
import org.dnyanyog.dto.CustomerResponse;
import org.dnyanyog.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

	public CustomerResponse addCustomer(CustomerRequest customerRequest);

	public CustomerResponse getSingleCustomer(Long id);

	public CustomerResponse updateCustomer(Long userId, Customer request);

	public CustomerResponse deleteCustomer(Long id);

}
