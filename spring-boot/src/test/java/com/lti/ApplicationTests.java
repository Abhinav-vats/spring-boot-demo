package com.lti;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;

import com.lti.repository.CustomerRepository;
import com.lti.entity.Customer;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class ApplicationTests {

	@Autowired
	private CustomerRepository customerRepo;

	@Test
	void testAddingCustomer() {

		Customer c = new Customer();
		c.setName("Abhinav");
		c.setEmail("abhi@lti.com");
		c.setPassword("1234");
		c.setDateOfBirth(LocalDate.of(1997, 11, 19));
		customerRepo.save(c);
	}
	
	@Test
	void fetchByMail() {
		int id = customerRepo.findByEmailAndPassword("abhi@lti.com", "1234");   //EmptyResultDataException 
		System.out.println(id);
	}

}
