package org.dnyanyog.repository;

import java.util.Optional;

import org.dnyanyog.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(long Id);
	
	Optional<Customer> findByEmail(String email);
	
}
