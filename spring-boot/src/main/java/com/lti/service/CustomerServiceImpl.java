package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public void  register(Customer customer) throws CustomerServiceException {
		if(!customerRepo.isCustomerAvailable(customer.getEmail()))
			customerRepo.save(customer);
		else 
			throw new CustomerServiceException("Customer Already Registered");
	}
	
	@Override
	public Customer login(String email, String password) {
		try {
			int id = (int) customerRepo.findByEmailAndPassword(email, password);
			Customer customer = customerRepo.findById(id);
			return customer;
		}
		catch(EmptyResultDataAccessException e) {
			throw new CustomerServiceException("Incorrect username or password");
		}
	}

}
